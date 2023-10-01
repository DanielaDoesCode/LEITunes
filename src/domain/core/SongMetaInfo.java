/**
 * 
 */
package domain.core;

import java.util.List;
import java.util.regex.Pattern;

import util.adts.RegExpMatchable;

/**
 * @author fc58199 - Daniela Camarinha
 * @author fc58193 - Rafael Ribeiro
 *
 */
public record SongMetaInfo(String title, String genre, List<String> artists, String album) implements RegExpMatchable {
	
	
	/**
	 * Checks if the MetaInfo on a Song matches the regex expression given
	 * @return true = if matches/false = otherwise
	 */
	@Override
	public boolean matches(String regexp) {
	  Pattern expression = Pattern.compile(regexp);
	  boolean matches = false;
	  for(int i = 0; i < artists.size(); i++) {
		  matches = matches || expression.matcher(artists.get(i)).matches();
	  }
	  
		return matches || (expression.matcher(title).matches()
				       || expression.matcher(genre).matches()
				       ||expression.matcher(album).matches());
	}

}
