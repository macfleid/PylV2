package com.mcfly.pyl.sqlite.views.wrapper;

import android.database.Cursor;
import com.mcfly.pyl.sqlite.cursor.ContactCursor;
import com.mcfly.pyl.sqlite.cursor.PlaylistCursor;
import com.mcfly.pyl.sqlite.dal.Contact;
import com.mcfly.pyl.sqlite.dal.Playlist;
import com.mcfly.pyl.sqlite.dal.wrapper.ContactDalWrapper;
import com.mcfly.pyl.sqlite.dal.wrapper.PlaylistDalWrapper;

public class Playlist_listVIEWDalWrapper {

   public static Playlist getPlaylist(Cursor cursor) {
      int start=0;
      return PlaylistDalWrapper.getObjectFromDB(new PlaylistCursor(cursor), start);
   }
 
   public static Contact getContact(Cursor cursor) {
      int start=0+
      PlaylistDalWrapper.getNbColumns();
      return ContactDalWrapper.getObjectFromDB(new ContactCursor(cursor), start);
   }
 
   public static int getNbShare(Cursor cursor) {
      int start=0+
      PlaylistDalWrapper.getNbColumns()+
      ContactDalWrapper.getNbColumns();
      return cursor.getInt(start);
   }
 
   public static int getNbSong(Cursor cursor) {
      int start=0+
      PlaylistDalWrapper.getNbColumns()+
      ContactDalWrapper.getNbColumns()+1;
      return cursor.getInt(start);
   }
 
}
