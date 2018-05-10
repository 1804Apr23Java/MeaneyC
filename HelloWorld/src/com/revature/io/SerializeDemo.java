package com.revature.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.revature.media.Movie;

public class SerializeDemo {

	public static void main(String[] args) {

		String filename = "src/serializedThings/Movies.txt";
		
		List<Movie> movieList = new ArrayList<Movie>();
		Movie m1 = new Movie("Steven Spielberg", "Jaws", 1975, "horror");
		Movie m2 = new Movie("David Michael Latt","Sharknado",2013,"science fiction");
		Movie m3 = new Movie("Quentin Tarantino","Pulp Fiction",1994,"Tarantino");
		movieList.add(m2);
		movieList.add(m1);
		movieList.add(m3);
		System.out.println("Movies before sort:");
		for(Movie m : movieList) {
			System.out.println(m);
		}
		
		//serializeObject(filename, movieList);
		System.out.println(deserializeObject(filename));
		
	}

	public static void serializeObject(String filename, Object o) {
		
		try {
			//use a FileOutputStream to write bytestream to a file
			FileOutputStream fileOut = new FileOutputStream(filename);
			//use a ObjectOutputStream to represent an object as a bytestream
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			//actually serialize the object
			out.writeObject(o);
			//close resources
			out.close();
			fileOut.close();
			System.out.println("serialization successful");
		} catch(FileNotFoundException f) {
			f.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object deserializeObject(String filename) {
		
		Object o = null;
		
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			o = in.readObject();
			in.close();
			fileIn.close();
		} catch(FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return o;
		
	}
	
	
}
