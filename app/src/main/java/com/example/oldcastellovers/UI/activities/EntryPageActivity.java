package com.example.oldcastellovers.UI.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.oldcastellovers.R;
import com.example.oldcastellovers.database.DataBaseHelper;
import com.example.oldcastellovers.database.models.DiaryEntryModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EntryPageActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_RECORD_AUDIO = 2;
    private static final int REQUEST_VIDEO_CAPTURE = 3;
    private ImageView saveIcon; // ImageView to display the taken media
    private boolean isRecording = false;
    private String currentPhotoPath;
    private Uri currentMediaUri; // Store the URI of the captured media

    // Define folder names
    private static final String AUDIO_FOLDER_NAME = "Audio";
    private static final String VIDEO_FOLDER_NAME = "Video";
    private static final String PICTURE_FOLDER_NAME = "Pictures";
    private List<Uri> currentMediaUris = new ArrayList<>();

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private TextView textViewCastleName, textViewLocation, textViewWebsite;
    private EditText largeTextInput;

    public ArrayList<String> mediaPaths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrypage);

        // Retrieve castle details from the intent
        Intent intent = getIntent();
        String castleName = intent.getStringExtra("castleName");
        String castleAddress = intent.getStringExtra("castleAddress");
        String castleWebsite = intent.getStringExtra("castleWebsite");

        textViewCastleName = findViewById(R.id.textViewCastleName);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewWebsite = findViewById(R.id.textViewWebsite);
        largeTextInput = findViewById(R.id.largeTextInput);
        saveIcon = findViewById(R.id.saveIcon);

        textViewCastleName.setText(castleName);
        textViewLocation.setText(castleAddress);
        textViewWebsite.setText(castleWebsite);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        // Create folders if they don't exist
        createAppFolders();

        // Find the "Attach File" icon
        ImageView attachmentIcon = findViewById(R.id.attachmentIcon);

        // Set an OnClickListener for the "Attach File" icon
        attachmentIcon.setOnClickListener(v -> {
            // Code to show the popup menu
            showPopupMenu(attachmentIcon);
        });

        saveIcon = findViewById(R.id.saveIcon);
//        saveIcon.setOnClickListener(v -> {
//            // Save the selected media items
//            for (Uri mediaUri : currentMediaUris) {
//                // Save the media to your local storage here
//                // You can use the mediaUri to access the media
//            }
//            // Clear the currentMediaUris list after saving
//            currentMediaUris.clear();
//
//            // Clear the mediaLayout to remove all displayed media items
//            LinearLayout mediaLayout = findViewById(R.id.mediaLayout);
//            mediaLayout.removeAllViews();
//        });
        saveIcon.setOnClickListener(new View.OnClickListener() {
            DiaryEntryModel diaryEntryModel;
            @Override
            public void onClick(View view) {
                try {
                    diaryEntryModel = new DiaryEntryModel(-1, dateButton.getText().toString(),textViewCastleName.getText().toString(), textViewLocation.getText().toString(), textViewWebsite.getText().toString(), largeTextInput.getText().toString(), mediaPaths);
                }catch (Exception e){
                    Toast.makeText(EntryPageActivity.this, "Error creating diary entry!!!", Toast.LENGTH_SHORT).show();
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(EntryPageActivity.this);
                boolean success = dataBaseHelper.addDiaryEntry(diaryEntryModel);

                if(success){
                    Toast.makeText(EntryPageActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EntryPageActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EntryPageActivity.this, DiaryEntryActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        switch (month){
            case 1: return "JAN";
            case 2: return "FEB";
            case 3: return "MAR";
            case 4: return "APR";
            case 5: return "MAY";
            case 6: return "JUN";
            case 7: return "JUL";
            case 8: return "AUG";
            case 9: return "SEP";
            case 10: return "OCT";
            case 11: return "NOV";
            case 12: return "DEC";
            default: return "ERR";
        }
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private void addMediaItemToLayout(Uri mediaUri) {
        // Create a new LinearLayout to hold the media item and remove button
        LinearLayout mediaItemLayout = new LinearLayout(this);
        mediaItemLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Create an ImageView for the media
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                300, 300  // Set a fixed size for the media item (adjust as needed)
        );
        imageView.setLayoutParams(imageParams);

        // Check if the media is a video and set the thumbnail accordingly
        if (isVideoFile(mediaUri.getPath())) {
            loadVideoThumbnail(imageView, mediaUri);
        } else {
            imageView.setImageURI(mediaUri);
        }

        // Create a remove button
        ImageButton removeButton = new ImageButton(this);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        buttonParams.setMargins(0, 0, 10, 0);  // Adjust margins as needed
        removeButton.setLayoutParams(buttonParams);
        removeButton.setImageResource(android.R.drawable.ic_delete);
        removeButton.setOnClickListener(v -> {
            // Remove the media item from the view
            mediaItemLayout.setVisibility(View.GONE);

            // Remove the media URI from the list
            currentMediaUris.remove(mediaUri);

            // Update the mediaLayout after removal
            updateMediaLayout();
        });

        // Add the ImageView and Remove button to the mediaItemLayout
        mediaItemLayout.addView(imageView);
        mediaItemLayout.addView(removeButton);

        // Add the mediaItemLayout to the mediaLayout
        LinearLayout mediaLayout = findViewById(R.id.mediaLayout);
        mediaLayout.addView(mediaItemLayout);
    }

    private void loadVideoThumbnail(ImageView imageView, Uri videoUri) {
        Glide.with(this)
                .load(videoUri)
                .into(imageView);
    }

    private boolean isVideoFile(String path) {
        // Implement this method to check if the file at the given path is a video file
        // You can use the file extension or other methods to determine the file type
        // For example, you can check if the path ends with ".mp4"
        return path.toLowerCase().endsWith(".mp4");
    }

    private void updateMediaLayout() {
        // Clear the mediaLayout to refresh the displayed media items
        LinearLayout mediaLayout = findViewById(R.id.mediaLayout);
        mediaLayout.removeAllViews();

        // Iterate through the currentMediaUris and add views to the mediaLayout
        for (Uri mediaUri : currentMediaUris) {
            addMediaItemToLayout(mediaUri);
        }
    }

    private void createAppFolders() {
        // Get the app's external storage directory
        File storageDir = getExternalFilesDir(null);

        // Create folders if they don't exist
        if (storageDir != null) {
            new File(storageDir, AUDIO_FOLDER_NAME).mkdirs();
            new File(storageDir, VIDEO_FOLDER_NAME).mkdirs();
            new File(storageDir, PICTURE_FOLDER_NAME).mkdirs();
        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.attachment_options_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_record_audio) {
                // Handle the record audio option
                if (!isRecording) {
                    // Request audio recording permission
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                        startRecordingAudio();
                    } else {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO);
                    }
                } else {
                    stopRecordingAudio();
                }
                return true;
            } else if (itemId == R.id.menu_take_picture) {
                // Handle the take picture option
                takePicture();
                return true;
            } else if (itemId == R.id.menu_take_video) {
                // Handle the take video option
                takeVideo();
                return true;
            }
            return false;
        });

        popupMenu.show();
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = createImageFile();
            currentMediaUri = FileProvider.getUriForFile(this,
                    "com.example.oldcastellovers.fileprovider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentMediaUri);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void takeVideo() {
        // Define the directory where you want to store videos
        File videoDir = new File(getExternalFilesDir(null), VIDEO_FOLDER_NAME);
        videoDir.mkdirs(); // Create the directory if it doesn't exist

        // Create a file to save the video
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String videoFileName = "VIDEO_" + timeStamp;
        File videoFile = new File(videoDir, videoFileName + ".mp4");

        currentMediaUri = FileProvider.getUriForFile(this, "com.example.oldcastellovers.fileprovider", videoFile);

        // Create the video capture intent
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentMediaUri);
        takeVideoIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
    }

    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = new File(getExternalFilesDir(null), PICTURE_FOLDER_NAME);
        storageDir.mkdirs(); // Ensure the folder exists
        File image = new File(storageDir, imageFileName + ".jpg");
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void startRecordingAudio() {
        // Your audio recording code here
    }

    private void stopRecordingAudio() {
        // Your audio recording stop code here
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start audio recording
                startRecordingAudio();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Display a Toast with the full path of the taken picture
            Toast.makeText(this, "Picture saved at: " + currentPhotoPath, Toast.LENGTH_SHORT).show();
            // Log the full path to Logcat
            Log.d("ImagePath", "Picture saved at: " + currentPhotoPath);
            mediaPaths.add(currentPhotoPath);
            // The picture was taken and saved to currentMediaUri
            // Add the media item to the layout
            addMediaItemToLayout(currentMediaUri);

            // Store the URI for future reference if needed
            currentMediaUris.add(currentMediaUri);
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Toast.makeText(this, "Video saved at: " + currentMediaUri.getPath(), Toast.LENGTH_SHORT).show();
            // Log the full path to Logcat
            Log.d("ImagePath", "Picture saved at: " + currentMediaUri);
            mediaPaths.add(currentMediaUri.getPath().toString());
            // The video was taken and saved to currentMediaUri
            // Add the media item to the layout

            addMediaItemToLayout(currentMediaUri);

            // Store the URI for future reference if needed
            currentMediaUris.add(currentMediaUri);
        }
        Log.d("Arraylist", "Content: " + mediaPaths);
    }
}