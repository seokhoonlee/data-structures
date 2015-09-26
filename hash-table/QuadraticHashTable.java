/**
 *	All values of the entry has to be of string type for this hash table.
 */
public class QuadraticHashTable {
	public static class Entry {
		public int key;
		public String value;

		public Entry(int inputKey, String inputValue) {
			this.key = inputKey;
			this.value = inputValue;
		}
	}

	public final int TABLE_SIZE = 11; // Preferably the size of Hash Table should be a prime number to avoid clustering.
	public final int EMPTY_INDEX_KEY = -1;
	public Entry[] array;

	public QuadraticHashTable() {
		array = new Entry[TABLE_SIZE];

		for (int i = 0; i < TABLE_SIZE; i++) {
			array[i] = new Entry(EMPTY_INDEX_KEY, " ");
		}
	}

	public Entry get(int inputKey) {
		int hash = (inputKey % TABLE_SIZE);
		int count = 1;

		while (array[hash].key != inputKey) {
			hash = (hash + (count * 2 - 1)) % TABLE_SIZE;
			count++;
			
			if (array[hash].value == " ") {
				break;
			}
		}

		return array[hash];
	}

	public boolean put(int inputKey, String inputValue) {
		int hash = (inputKey % TABLE_SIZE);
		int count = 1;

		while (array[hash].value != " ") {
			hash = (hash + (count * 2 - 1)) % TABLE_SIZE;
			count++;
		}
		
		if (array[hash].value == " ") {
			array[hash] = new Entry(inputKey, inputValue);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean delete(int inputKey) {
		Entry deleteEntry = get(inputKey);
		
		if (deleteEntry.value == " ") {
			return false;
		} else {
			deleteEntry.key = EMPTY_INDEX_KEY;
			deleteEntry.value = " ";
		}
		
		return false;
	}

	public void print() {
		for (int i = 0; i < TABLE_SIZE; i++) {
			System.out.print("|");
			if (array[i] != null) {
				System.out.print(array[i].value);
			} else {
				System.out.print(" ");
			}
		}

		System.out.print("|\n");
	}

	public static void main(String[] args){
		QuadraticHashTable table = new QuadraticHashTable();

		table.put(1, "A");
		table.put(3, "C");
		table.put(14, "N");
		System.out.print(table.get(3).value + "\n");
		System.out.print(table.get(13).value + "\n");
		System.out.print(table.get(14).value + "\n");
		table.print();
		table.put(12, "L");
		table.put(23, "W");
		table.delete(3);
		table.print();

		/**
		 * Output:
		 * C
		 *  
		 * N
		 * | |A| |C|N| | | | | | |
		 * | |A|L| |N|W| | | | | |
		 */
	}
}