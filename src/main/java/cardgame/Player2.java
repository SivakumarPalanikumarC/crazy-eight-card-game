package cardgame;

import java.util.List;

public class Player2 implements PlayerStrategy{
	int playerId;
	List<Integer> opponentIds;
	List<Card> myCards;
	Card topPileCard;
	Card.Suit changedSuit;
	public void init(int playerId, List<Integer> opponentIds) {
		this.playerId=playerId;
		this.opponentIds=opponentIds;
	}
	public void receiveInitialCards(List<Card> cards) {
		this.myCards=cards;
			
	}
	public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
		this.topPileCard=topPileCard;
		this.changedSuit=changedSuit;
		if(changedSuit==null) {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit())||myCards.get(i).getRank().equals(topPileCard.getRank())) {
					return false;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					return false;
				}
			}
		}
		return true;
		
	}
	public void receiveCard(Card drawnCard) {
		System.out.println("Player2 recieved :"+drawnCard.getRank()+" "+drawnCard.getSuit());
		myCards.add(drawnCard);
	}
	public Card playCard() {
		Card outCard=null;
		if(changedSuit==null) {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit())||myCards.get(i).getRank().equals(topPileCard.getRank())) {
					System.out.println("Player2 played: "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					outCard=myCards.get(i);
					myCards.remove(i);
					break;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					System.out.println("Player2 played: "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					outCard=myCards.get(i);
					myCards.remove(i);
					break;
				}
			}
		}
		return outCard;
		
	}
	public Card.Suit declareSuit(){
		Card Dsiut=myCards.get(0);
		int max=52,count=0;
		for(int i=0;i<myCards.size();i++) {
			count=0;
			for(int j=0;j<myCards.size();j++) {
				if(myCards.get(i)==myCards.get(j))
					count++;
			}
			if(count<max) {
				max=count;
				Dsiut=myCards.get(i);
			}
		}
		System.out.println("Delcare suit: "+Dsiut.getSuit());
		return Dsiut.getSuit();
		
	}
	public void processOpponentActions(List<PlayerTurn> opponentActions) {
		
	}
	 public void reset() {
		 
	 }
	 @Override
		public int getScore() {
			int point=0;
			for(int i=0;i<myCards.size();i++) {
				point+=myCards.get(i).getPointValue();
			}
			return point;
		}
}
