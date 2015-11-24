CREATE VIEW [Playlist_listVIEW] AS 
Select Playlist.*, Contact.*, nbShare.nbPlaylistShare, nbSongs.nbSongs   
FROM Playlist 
LEFT OUTER JOIN Contact ON Contact._id = Playlist.Contact__id 
LEFT OUTER JOIN  
( 
	SELECT count(*) as nbPlaylistShare, Playlist__id as argPlaylistId 
	FROM Contact_has_Playlist 
) as nbShare ON nbShare.argPlaylistId = Playlist._id 
LEFT OUTER JOIN 
(
	SELECT count(*) as nbSongs, Playlist__id as argPlaylistId 
	FROM Song 
) as nbSongs ON nbSongs.argPlaylistId = Playlist._id;