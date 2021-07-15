package sg.edu.rp.c346.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String folderLocation;
    //UI handlers to be defined
    Button btnWrite, btnRead;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //UI handlers to be defined
        btnRead=findViewById(R.id.btnRead);
        btnWrite=findViewById(R.id.btnWrite);
        tv = findViewById(R.id.tv);


        //Folder creation
        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";

        File folder = new File(folderLocation);
        if (folder.exists() == false){
            boolean result = folder.mkdir();
            if (result == true){
                Log.d("File Read/Write", "Folder created");
            }
        }
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file writing
                try{
                    folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder";
                    File targetFile = new File(folderLocation,"data.txt");
                    FileWriter writer = new FileWriter(targetFile,true);
                    writer.write("Hello World" + "\n");
                    writer.flush();
                    writer.close();
                    Toast.makeText(getApplicationContext(),"Created the tex",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Unable to create text",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for file reading
                String folderLocation= Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder";
                File targetFile= new File(folderLocation, "data.txt");
                if (targetFile.exists() == true){String data ="";try {
                    FileReader reader = new FileReader(targetFile);
                    BufferedReader br= new BufferedReader(reader);
                    String line = br.readLine();
                    while (line != null){
                        data += line + "\n";
                        line = br.readLine();

                    }
                    tv.setText(data);
                    br.close();
                    reader.close();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();e.printStackTrace();
                }
                    Log.d("content", data);
                }
            }
        });


    }
}