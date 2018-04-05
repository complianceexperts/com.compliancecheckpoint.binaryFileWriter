

package com.compliancecheckpoint.binaryFileWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

public class BinaryFileWriterPlugin extends CordovaPlugin {

	 @Override
	 public boolean execute(String action, JSONArray args, CallbackContext cbc) throws JSONException {
	        
	        String result = "";
	        JSONObject arg_object = args.getJSONObject(0);
	        
	        try {
	            if (action.equals("writeToFile")) {
	                
	            	byte[] pdfAsBytes =  Base64.decode(arg_object.getString("base64data").getBytes(), 0);
	            	String theFileName = arg_object.getString("fileName");
	            		            	
	            	if(theFileName.isEmpty())
	            		theFileName = "tempfile.dat";

					String theFileUrl = "";
					if(theFileName.startsWith("file://")) {
						theFileUrl = theFileName.replace("file://", "");
					}
					else {
						theFileUrl = this.cordova.getActivity().getExternalCacheDir().getAbsolutePath() + File.separator + theFileName;
					}
	            	
	            	//Log.v("BinaryFileWriterPlugin", arg_object.getString("base64data"));
	            	
	            	File filePath = new File(theFileUrl);
	            	
	            	if(filePath.exists()){
	            		
	            		filePath.delete();
	            		
	            	}
	            	
	            	filePath.createNewFile();
	            	
	            	if(filePath.exists())
	            	{
		            	FileOutputStream os = new FileOutputStream(filePath);
		            	os.write(pdfAsBytes);
		            	os.flush();
		            	os.close();
	            	}
	            	
	            	//JSONObject resultJson = new JSONObject("{'fileUri':'" + filePath.toURL().toString() +"'}");
	            	JSONObject resultJson = new JSONObject("{'fileUri':'" + theFileUrl +"'}");

	            	cbc.success(resultJson);
	            	
	                return true;
	            }
	            else
	            	return false;
	        } catch (Exception e) {
	        	cbc.error("Can not write to file");
	            Log.v("BinaryFileWriterPlugin", e.getMessage() + "\n" + e.getStackTrace().toString());
	            return false;
	        }
	 }
	
}
