package codepath.demos.helloworlddemo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class HelloWorldActivity extends Activity {

	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	ListView lvItems;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_world);



		itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		lvItems = (ListView) findViewById(R.id.lvitems);

		// mock data
		items.add("First item");
		items.add("Second item");

		readItems();

		setupListViewListener();
	}

	public void onAddItem(View v) {
		EditText etNewItem = (EditText) findViewById(R.id.etNewitem);
		String itemText = etNewItem.getText().toString();
		itemsAdapter.add(itemText);
		etNewItem.setText("");
		writeItems();
		Toast.makeText(getApplicationContext(), "Item added to the list", Toast.LENGTH_SHORT).show();


	}

	private void setupListViewListener() {
		Log.i("HelloWorldActivity", "Setting up listener on list view");
        lvItems.setAdapter(itemsAdapter);
        lvItems = (ListView)
                findViewById(R.id.lvitems);

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Log.i("HelloWorldActivity", "Item removed from list: " + position);
				items.remove( position);
				itemsAdapter.notifyDataSetChanged();
				writeItems();
				return true;
			}
		});


        //set up Item listener for edit(regular click)
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //create new activity
                Intent i = new Intent(HelloWorldActivity.this, EditItemActivity.class);

                //pass the data being edited
                i.putExtra("ITEM_TEXT", items.get(position));
                i.putExtra("ITEM_POSITION", position);

                //display the activity
               /// startActivityForResult(i, Edit_REQUEST_CODE);
            }
        });
    }
	private File getDataFile() {
		return new File(getFilesDir(), "todo.txt");
	}

	private void readItems() {
		try {
			items = new ArrayList<>(org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
		}catch (IOException e) {
			Log.e("HelloWorldActivity", "Error reading file", e);
			items = new ArrayList<>();
		}
	}
	private void writeItems(){
		try {
			FileUtils.writeLines(getDataFile(), items);
		}catch (IOException e) {
			Log.e("HelloWorldActivity", "Error writing file", e);
		}

	}
}
