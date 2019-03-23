package com.example.letsgo;
import android.content.ClipData;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView istView;
    MaterialSearchView searchView;

    String[] istSource={
            "monika",
            "savi",
            "ankush",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        Toolbar toolbar=(Toolbar) findViewById ( R.id.toolbar );
        setSupportActionBar(toolbar);
        getSupportActionBar ().setTitle ( "Material search" );
        toolbar.setTitleTextColor(Color.parseColor(("#ffffff")));

        istView=(ListView)findViewById ( R.id.listview );
        ArrayAdapter adapter=new ArrayAdapter ( this,android.R.layout.simple_list_item_1,istSource);
        istView.setAdapter ( adapter );

        searchView = (MaterialSearchView) findViewById ( R.id.search_view  );

       // @Override


        searchView.setOnSearchViewListener ( new MaterialSearchView.SearchViewListener () {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

                //if closed  search view,istView will return default

                istView=(ListView)findViewById ( R.id.listview );
                ArrayAdapter adapter=new ArrayAdapter ( MainActivity.this,android.R.layout.simple_list_item_1,istSource);
                istView.setAdapter ( adapter );



            }
        } );

        searchView.setOnQueryTextListener ( new MaterialSearchView.OnQueryTextListener () {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            public boolean onQueryTextChange(String newText) {
                if(newText!=null && !newText.isEmpty ())
                {
                    List<String> istFound=new ArrayList <String> (  );
                    for (String item: istSource){
                        if(item.contains(newText))
                        {
                            istFound.add(item);
                        }
                    }

                    ArrayAdapter adapter=new ArrayAdapter ( MainActivity.this,android.R.layout.simple_list_item_1,istSource);
                    istView.setAdapter ( adapter );


                }

                else
                {
                    //return default

                    ArrayAdapter adapter=new ArrayAdapter ( MainActivity.this,android.R.layout.simple_list_item_1,istSource);
                    istView.setAdapter ( adapter );
                }
                return true;
            }
        } );
    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater ().inflate ( R.menu.menu_item,menu );
        MenuItem item=menu.findItem ( R.id.action_search );
        searchView.setMenuItem ( item );
        return true;
    }

}
