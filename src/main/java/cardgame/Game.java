package cardgame;

import java.util.*;
import cardgame.Card.*;

public class Game {
		
	public static void main(String[] args) {		
		Game ob=new Game();
		Player1 play1=new Player1();
		Player2 play2=new Player2();
		List<Card> deck=new ArrayList<>();
		deck=Card.getDeck();
		Collections.shuffle(deck);
		deck=ob.start(deck,play1,play2);
		ob.play(deck,play1,play2);;
		
	}
	/**
	 * start function for start and restart the game
	 * @param deck passing the deck for pass the Cards to the player 
	 * @param play1 @param play2 for access the recieveIntialCard function
	 * @return deck because we need the updated deck;
	 */
	List<Card> start(List<Card> deck, Player1 play1, Player2 play2) {
		int i;
		List<Card> player1=new ArrayList<>();
		List<Card> player2=new ArrayList<>();
		for(i=0;i<14;i++) {
			if(i%2==0)
				player2.add(deck.get(0));
			else 
				player1.add(deck.get(0));
			deck.remove(0);			
		}
		System.out.println();
		System.out.println("Player1 Cards : ");
		for(i=0;i<player1.size();i++) {
			System.out.print(player1.get(i).getRank()+" "+player1.get(i).getSuit()+" ");
		}
		System.out.println("Player2 Cards : ");
		for(i=0;i<player2.size();i++) {
			System.out.print(player2.get(i).getRank()+" "+player2.get(i).getSuit()+" ");
		}
		System.out.println();
		play1.receiveInitialCards(player1);
		play2.receiveInitialCards(player2);
		return deck;
	}
	/**
	 * Play function for play the Crazy 8's and get points
	 * @param deck is passing for access the deck cards to play the game;
	 * @param play1 @param play2 for access their game Strategy and their Cards 
	 */
	void play(List<Card> deck, Player1 play1, Player2 play2) {
		Game ob=new Game();
		int point1=0,i,point2=0;
		Card topCard;
		topCard=deck.get(0);
		deck.remove(0);
		System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
		Card.Suit decCard=null;
		while(point1<200&&point2<200) {
			for(i=0;i<3;i++) {
				if(play2.shouldDrawCard(topCard, decCard)) {
					if(deck.size()!=0) {
						play2.receiveCard(deck.get(0));		
						deck.remove(0);			
					}
				}
				else {
					topCard=play2.playCard();
					System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
					if(topCard.getRank()==Rank.EIGHT&&play2.myCards.size()!=0) {
						decCard=play2.declareSuit();
					}
					break;
				}
			}
			for(i=0;i<3;i++) {
				if(play1.shouldDrawCard(topCard, decCard)) {
					if(deck.size()!=0) {
						play1.receiveCard(deck.get(0));	
						deck.remove(0);
						
					}
				}
				else {
					topCard=play1.playCard();
					System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
					if(topCard.getRank().equals(Rank.EIGHT)&&play1.myCards.size()!=0) {
						decCard=play1.declareSuit();
					}
					break;
				}
			}
			if(play1.myCards.size()==0||deck.size()==0) {
				point2+=play2.getScore();
				System.out.println("player2 :"+point2);
			}
			if(play2.myCards.size()==0||deck.size()==0) {
				point1+=play1.getScore();
				System.out.println("player1 :"+point1);
			}
			if(deck.size()==0&&point1<200&&point2<200) {
				deck=Card.getDeck();
				Collections.shuffle(deck);
				deck=ob.start(deck,play1,play2);
			}
		}
		ob.results(point1,point2);
	}
	/**
	 * results function for show the results in console
	 * @param p1 @param p2 are the points of the player 1 and player 2;
	 */
	void results(int p1,int p2) {
		if(p1>=200) {
			System.out.println("player2 wins");
		}
		else if(p2>=200) {
			System.out.println("player1 wins");
		}
	}

}
