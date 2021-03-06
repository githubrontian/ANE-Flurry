/*
 * Copyright 2017 FreshPlanet
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.freshplanet.ane.AirFlurry.functions;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import com.adobe.fre.FREArray;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.freshplanet.ane.AirFlurry.AirFlurryExtension;
import com.freshplanet.ane.AirFlurry.AirFlurryExtensionContext;

public class BaseFunction implements FREFunction {
	@Override
	public FREObject call(FREContext context, FREObject[] args) {
		AirFlurryExtension.context = (AirFlurryExtensionContext)context;
		return null;
	}
	
	protected String getStringFromFREObject(FREObject object) {
		try {
			return object.getAsString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Boolean getBooleanFromFREObject(FREObject object) {
		try {
			return object.getAsBool();
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected int getIntFromFREObject(FREObject object) {
		try {
			return object.getAsInt();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	protected double getDoubleFromFREObject(FREObject object) {
		try {
			return object.getAsDouble();
		}
		catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	protected List<String> getListOfStringFromFREArray(FREArray array) {
		List<String> result = new ArrayList<String>();
		
		try {
			for (int i = 0; i < array.getLength(); i++) {
				try {
					result.add(getStringFromFREObject(array.getObjectAt((long)i)));
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	protected Bundle getBundleOfStringFromFREArrays(FREArray keys, FREArray values) {
		Bundle result = new Bundle();
		
		try {
			long length = Math.min(keys.getLength(), values.getLength());
			for (int i = 0; i < length; i++) {
				try {
					String key = getStringFromFREObject(keys.getObjectAt((long)i));
					String value = getStringFromFREObject(values.getObjectAt((long)i));
					result.putString(key, value);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
}
