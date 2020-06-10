package com.semanticsquare.thrillio.util;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class IOUtil {
	public static void read(List data, String filename) {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF=8" )) ){
	String line;
				
				while((line=br.readLine())!=null) {
					data.add(line);
					
				}
		}
				catch(UnsupportedEncodingException e) {
			e.printStackTrace();	}
				catch(FileNotFoundException e) {
					e.printStackTrace();	}
				catch(IOException e) {
					e.printStackTrace();}
					
				
				}

	public static String read(InputStream in) {
	StringBuilder text=new StringBuilder();
		try(BufferedReader br=new BufferedReader (new InputStreamReader(in,"UTF=8" )) ){
			String line;
						
						while((line=br.readLine())!=null) {
							text.append(line+"\n");
						}
		}
						catch(UnsupportedEncodingException e) {
							e.printStackTrace();
						}
		catch(IOException e) {
		e.printStackTrace();
		}
						
				return text.toString();		}
	
	
	 public static void write(String webpage, long id) {
		 try(BufferedWriter br=new BufferedWriter (new OutputStreamWriter(new FileOutputStream("C:/jid/thrillio/pages/"+String.valueOf(id)+".html"),"UTF=8" )) ){
			br.write(webpage);
			 			}
			 	 catch(UnsupportedEncodingException e) {
			 		 e.printStackTrace();
			 	 }catch(FileNotFoundException e) {
			 		 e.printStackTrace();
	              } catch(IOException e) {
			 		 e.printStackTrace();
			 	 }
			 	
	 }
	public static void serialize(Object obj, String filename) {
		if (obj != null) {
			try (ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(filename)))) {
				out.writeObject(obj);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Object deserialize(String filename) {
		try (ObjectInputStream out = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
			return out.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
