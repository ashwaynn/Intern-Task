package io.picopalette.apps.interntask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<TaskQandA> objList = new ArrayList<TaskQandA>();
    private RecyclerView mRecylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecylerView = findViewById(R.id.taskRecyclerView);

        JsonObjectRequest taskDataRequest = new JsonObjectRequest(Request.Method.GET, "http://mysummerinterntask.proxy.beeceptor.com/interndata", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", response.toString());
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                TaskQandA taskQandA;
                                JSONObject qaPair = jsonArray.getJSONObject(i);
                                taskQandA = new TaskQandA(qaPair.getString("q"),qaPair.getString("a"));
                                objList.add(taskQandA);
                            }
                            TaskAdapter taskAdapter = new TaskAdapter(objList);
                            mRecylerView.setAdapter(taskAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            mRecylerView.setLayoutManager(layoutManager);
                            mRecylerView.setHasFixedSize(true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
                //Helpers.handleNetworkError(error, getActivity());
            }
        });
        NetworkHelper.getInstance(this).getRequestQueue().add(taskDataRequest);
    }

}
