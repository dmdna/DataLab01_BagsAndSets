package ciic4020.set.test;

import java.util.Scanner;

import ciic4020.set.Set;
import ciic4020.set.StaticSet;
import ciic4020.set.DynamicSet;

public class SetLabTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("This program supports static (S) and dynamic (D) sets!");
		System.out.println("What type of set do you wish to use (S/D): ");
		String input = in.nextLine();
		Set<String> theSet;
		
		if (input.equals("S")) {
			theSet = new StaticSet<String>(7);
		} else if (input.equals("D")) {
			theSet = new DynamicSet<String>(7);
		} else {
			in.close();
			throw new IllegalArgumentException("Option not recognized");
		}
		
		theSet.add("Bob");
		theSet.add("Jil");
		theSet.add("Ned");
		theSet.add("Apu");
		theSet.add("Ned");
		theSet.add("Amy");
		printSet(theSet);

		System.out.println("Is Bob member of the bag?: " + theSet.isMember("Bob"));
		System.out.println("Is Li member of the bag?: " + theSet.isMember("Li"));
		theSet.remove("Ned");
		System.out.println("Is the Bag empty: " + theSet.isEmpty());
		printSet(theSet);
		theSet.clear();
		System.out.println("Is the Bag empty: " + theSet.isEmpty());
		System.out.println("Elements:");
		printSet(theSet);

		// redo the Set
		theSet.add("Bob");
		theSet.add("Jil");
		theSet.add("Ned");
		theSet.add("Apu");
		theSet.add("Ned");
		theSet.add("Amy");

		Set<String> S2 = input.equals("S") ? new StaticSet<String>(10) : new DynamicSet<String>(10);
		S2.add("Amy");
		S2.add("Jil");
		S2.add("Moe");
		S2.add("Ned");
		System.out.println("Union: ");
		printSet(theSet.union(S2));

		System.out.println("difference theSet - S2: ");
		printSet(theSet.difference(S2));

		System.out.println("difference S2 - theSet: ");
		printSet(S2.difference(theSet));
		
		System.out.println("Intersection: ");
		printSet(S2.intersection(theSet));
		
		System.out.println("S2.isSubSet(theSet): " + S2.isSubSet(theSet));
		S2.remove("Moe");
		System.out.println("S2.isSubSet(theSet): " + S2.isSubSet(theSet));
		
		//EQUALS TESTER
		Set<String> S3 = input.equals("S") ? new StaticSet<String>(10) : new DynamicSet<String>(10);
		S3.add("Bob");
		S3.add("Jil");
		S3.add("Ned");
		S3.add("Apu");
		S3.add("Ned");
		S3.add("Amy");
		printSet(S3);
		
		
		System.out.println("Is S3 equal to theSet?: " + S3.equals(theSet));
		System.out.println("Is theSet equal to S3?: " + theSet.equals(S3));
		System.out.println("Is S3 equal to S2?: " + S3.equals(S2));
		
		
		//DISJOINT TESTER
		theSet.clear();
		S2.clear();
		S3.clear();
		
		theSet.add("Amy");
		theSet.add("Bob");
		theSet.add("Jil");
		printSet(theSet);
		
		S2.add("Ned");
		S2.add("Bob");
		printSet(S2);
		
		S3.add("Moe");
		S3.add("Apu");
		printSet(S3);
		
		Object[] sets = {theSet, S2, S3};
		
		System.out.println("Check Disjoint (should be true): " + checkDisjoint(sets));
		
		S3.add("Bob");
		printSet(S3);
		
		System.out.println("Check Disjoint (should be false): " + checkDisjoint(sets));
		
		
		System.out.println("Done!");
		in.close();
	}

	@SuppressWarnings("rawtypes")
	private static void printSet(Set theSet) {
		for (Object obj : theSet)
			System.out.println(obj);
		System.out.println("Set size: " + theSet.size());
	}
	
	//CHECK DISJOINT
	@SuppressWarnings("unchecked")
	public static boolean checkDisjoint(Object[] sets) {
		Set<Integer> inters = ((Set<Integer>)sets[0]);
		for (int i = 1; i < sets.length; i++) {
			inters = inters.intersection(((Set<Integer>)sets[i]));
		}
		return inters.isEmpty();
	}
}
