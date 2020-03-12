	import java.util.*;

	import java.util.ArrayList;

public class MandateCounter {

		public static void main (String[] args) {
			             Scanner sc = new Scanner(System.in);

						 System.out.println("Hur många mandat finns det?");
						 int numberOfMandat = sc.nextInt(); 

						 System.out.println("Gräns för småpartier?");
						 int limitForSmallParties = sc.nextInt();

						 System.out.println("Hur många partier deltog?");
						 int numberOfParties = sc.nextInt();
						 
						
					    System.out.println("Ange partiernas förkortningar");
					    String[] partiesShortening = new String[numberOfParties];
					    for (int i = 0; i < numberOfParties; i++) {
					    	String input = sc.next();
					    	partiesShortening[i] = input;
					    }
					    
					    System.out.println("Ange hur många röster varje parti fått");
					    int[] numberOfVotes = new int[numberOfParties];
					    for (int i = 0; i < numberOfParties; i++) {
					    	int input = sc.nextInt();
					    	numberOfVotes[i] = input;
					    }
						

						int[] mandat = splitMandat(numberOfMandat, limitForSmallParties, numberOfVotes);

						for (int i = 0; i < mandat.length; i++) {
							System.out.print(String.format("%s %d, ", partiesShortening[i], mandat[i]));
						}
					}

					public static int[] splitMandat(int numberOfMandat, int limit, int[] votes) {
						double[] jfrtal = new double[votes.length];
						int[] mandat = new int[votes.length];

						// 2
						int totalNumberOfVotes = getTotalNumberOfVotes(votes);
						for (int i = 0; i < votes.length; i++) {

							float percent = (float) votes[i] / (float) totalNumberOfVotes * 100;
							if (percent < limit) {
								votes[i] = 0;
							}
						}

						// initialize jfrtal 3
						for (int i = 0; i < votes.length; i++) {
							jfrtal[i] = votes[i] / 1.2;
						}

						while (numberOfMandat > 0) {
							int index = getHighestJfrtalIndex(jfrtal);

							mandat[index]++;
							jfrtal[index] = votes[index] / ((2 * mandat[index]) + 1); // 4

							numberOfMandat--;
						}

						return mandat;
					}

					public static int getHighestJfrtalIndex(double[] jfrtal) {

						int highestIndex = 0;

						for (int i = 0; i < jfrtal.length; i++) {

							if (jfrtal[i] > jfrtal[highestIndex]) {
								highestIndex = i;
							}
						}

						return highestIndex;
					}

					public static int getTotalNumberOfVotes(int[] numberOfVotes) {

						int totalVotes = 0;
						for (int i = 0; i < numberOfVotes.length; i++) {
							totalVotes += numberOfVotes[i];
						}

						return totalVotes;
		}
	}




