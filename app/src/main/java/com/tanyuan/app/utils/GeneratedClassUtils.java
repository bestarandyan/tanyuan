/**
 * 
 */
package com.tanyuan.app.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * @author liuxingxing
 * 
 */
public abstract class GeneratedClassUtils {

	public static Class<?> get(Class<?> clazz) {

		if (clazz == null)
			throw new RuntimeException("The clazz cannot be null!");

		if (clazz.getCanonicalName().endsWith("_"))
			return clazz;

		String name = clazz.getCanonicalName() + "_";

		try {
			Class<?> result = Class.forName(name);
			return result;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find class for" + name, e);
		}

	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	public static <T> T getInstance(Class<T> clazz) {
		try {
			if (clazz == null)
				throw new RuntimeException("The clazz cannot be null!");

			if (clazz.getCanonicalName().endsWith("_"))
				return clazz.newInstance();

			String name = clazz.getCanonicalName() + "_";

			try {
				@SuppressWarnings("unchecked")
				Class<T> result = (Class<T>) Class.forName(name);
				return result.newInstance();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Cannot find class for" + name, e);
			}

		} catch (InstantiationException | IllegalAccessException e1) {
			throw new RuntimeException(e1);
		}

	}

}
