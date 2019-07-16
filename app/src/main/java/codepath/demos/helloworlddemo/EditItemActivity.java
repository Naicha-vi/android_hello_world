package codepath.demos.helloworlddemo;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.app.Activity.RESULT_OK;

public class EditItemActivity extends AppCompatActivity {


    //track edit text
    EditText etItemText;

    //position of edited item in list
    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        // resolve edited text from layout
        etItemText = (EditText) findViewById(R.id.etItemText);

        //set edit text value form intent extra
        etItemText.setText(getIntent().getStringExtra("ITEM_TEXT"));

        // update position form intent extra
        position = getIntent().getIntExtra("ITEM_POSITION", 0);

        //update the title bar of the activity
        getSupportActionBar().setTitle("Edit Item");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //handle save button
    public void onSavedItem(View v){

        Intent i = new Intent();
        i.putExtra("ITEM_TEXT", etItemText.getText().toString());
        i.putExtra("ITEM_POSITION", position);
        setResult(RESULT_OK);
        finish();
    }


   // private void setResult(int resultOk) {

}
