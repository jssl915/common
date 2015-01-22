package com.system.prg.util;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

public class ByteList implements Serializable {
	private static final long serialVersionUID = -3960956561728079207L;
	private byte[] elementData;
	private int size;

	public ByteList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
		}
		this.elementData = new byte[initialCapacity];
	}

	public ByteList() {
		this(10);
	}

	public void ensureCapacity(int minCapacity) {
		int oldCapacity = this.elementData.length;
		if (minCapacity > oldCapacity) {
			byte[] oldData = this.elementData;
			int newCapacity = oldCapacity + 10;
			this.elementData = new byte[newCapacity];
			System.arraycopy(oldData, 0, this.elementData, 0, this.size);
		}
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public byte[] toArray() {
		byte[] result = new byte[this.size];
		System.arraycopy(this.elementData, 0, result, 0, this.size);
		return result;
	}

	public ByteArrayInputStream getByteArrayInputStream() {
		return new ByteArrayInputStream(this.elementData, 0, this.size);
	}

	public byte get(int index) throws IndexOutOfBoundsException {
		rangeCheck(index);
		return this.elementData[index];
	}

	public boolean add(byte o) {
		ensureCapacity(this.size + 1);
		this.elementData[(this.size++)] = o;
		return true;
	}

	public boolean addAll(byte[] bs) {
		if (bs != null) {
			for (int i = 0; i < bs.length; i++) {
				add(bs[i]);
			}
		}
		return true;
	}

	public boolean addAll(ByteList bList) {
		if (bList != null) {
			for (int i = 0; i < bList.size; i++) {
				add(bList.get(i));
			}
		}
		return true;
	}

	public int remove(int index) throws IndexOutOfBoundsException {
		rangeCheck(index);

		int oldValue = this.elementData[index];

		int numMoved = this.size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(this.elementData, index + 1, this.elementData,
					index, numMoved);
		}
		this.elementData[(--this.size)] = 0;

		return oldValue;
	}

	private void rangeCheck(int index) throws IndexOutOfBoundsException {
		if (index >= this.size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ this.size);
	}

	public void clear() {
		for (int i = 0; i < this.size; i++) {
			this.elementData[i] = 0;
		}
		this.size = 0;
	}

	public String toString(int offset, int length) {
		return new String(this.elementData, offset, length);
	}

	public String toString() {
		return new String(this.elementData, 0, this.size);
	}
}