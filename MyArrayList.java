package lab_06;

class MyArrayList {
	private double[] array;
	private int size;

	public MyArrayList() {
		this.array = new double[1];
		this.size = 0;
	}

	public double get(int i) {
		return array[i];
	}
	
	public void set(int index, double number) {
		 array[index] = number;   
    }
	
	public void setArray(double[] array) {
        this.array = array;
    }
	
	public void setSize(int size) {
        this.size = size;
    }
	
	private void grow() {
		double[] newArray = new double[array.length * 2];
		System.arraycopy(array, 0, newArray, 0, array.length);
		array = newArray;
	}
	public void add(double number) {
		if (size == array.length) {
		grow();
		}
		array[size++] = number;
	}

	public void insert(int index, double number) {
        add(number);
        for (int i = size - 1; i < index; i++) {
            array[i] = array[i - 1];
        }
        array[index] = number;
    }

	public void remove(int index) {
		int arrayLength = array.length;
		for (int i = index; i < arrayLength-1; i++) {
			array[i] = array[i+1];
		}
		size = arrayLength-1;
	}
	
	public int indexOf(double value) {
		int index = -1;
		for (int i = 0; i < size; i++) {
			if(array[i] == value) {
				index = i;
			}
		}
		return index;
	}

	public String toString() {
		StringBuilder stringArray = new StringBuilder("[");
		for (int i = 0; i < size; i++) {
            stringArray.append(array[i]);
            if (i != size - 1) {
                stringArray.append(", ");
            }
        }
		stringArray.append("]");
		return stringArray.toString();
	}
	
	public  int size() {
		return size;
	}

	public double[] getArray() {
		return array;
	}
}
