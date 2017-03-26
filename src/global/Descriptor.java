package global;

import java.io.IOException;
import java.lang.Math.*;

public class Descriptor {
	int[] value = new int[5];

	public void set(int value0, int value1, int value2, int value3, int value4) {
		value[0] = value0;
		value[1] = value1;
		value[2] = value2;
		value[3] = value3;
		value[4] = value4;
	}

	void setIndex(int idx, int value) {
		this.value[idx] = value;
	}

	public int get(int idx) {
		return value[idx];
	}

	// return 1 if equal; 0 if not
	double equal(Descriptor desc) {
		for (int i = 0; i < desc.value.length; i++) {
			if (this.value[i] != desc.value[i])
				return 0;
			else
				return 1;

		}
		return 0;
	}

	public double distance(Descriptor desc) {
		// return the Euclidean distance between the descriptors
		double dist = 0;
		for (int i = 0; i < desc.value.length; i++) {
			dist += (this.value[i] - desc.value[i]) * (this.value[i] - desc.value[i]);
		}
		return Math.sqrt(dist);
	}

	public void printDescriptor() throws IOException {
		for (int i = 0; i < 4; i++) {
			System.out.print(value[i] + " ");
		}
		System.out.print(value[4]);
	}

}
