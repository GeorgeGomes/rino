package com.seismaismais.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class SplitList {

	@Test
	public void test() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		
		List<Integer> bigList =list;
		List<List<Integer>> smallerLists = Lists.partition(bigList, 4);
		
		List<Integer> list1 =smallerLists.get(0);
		List<Integer> list2 =smallerLists.get(1);
		
		Collections.reverse(list2);
		
	}

}
