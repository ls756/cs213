/*
 * Le Sun 		RUID: 154008594
 * Shuhan Liu 	RUID: 154007082
 */

package songlib.view;

import java.util.Comparator;

import songlib.model.Song;

public class myComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		o1 = (Song)o1;
		o2 = (Song)o2;
		return ((Song) o1).getName().compareToIgnoreCase(((Song) o2).getName());
	}
}
