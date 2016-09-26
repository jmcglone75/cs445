//Gerard McGlone
//cs445

public class Blackjack
{
	public static void main(String[] args)
	{
			int numRounds, numDecks, originalShoeSize, playerValue, dealerValue, playerSize, dealerSize, discardSize;
			int dealerWins = 0, playerWins = 0, pushes = 0;
			boolean playerAceIsReduced = false, dealerAceIsReduced = false;
			
			numRounds = Integer.parseInt(args[0]);
			numDecks = Integer.parseInt(args[1]);
			
			System.out.println("\nStarting Blackjack with " + numRounds + " rounds and " + numDecks + " decks in the shoe.");
			
			originalShoeSize = 52*numDecks;
			
			RandIndexQueue<Card> shoe = new RandIndexQueue<Card>(originalShoeSize); //create shoe
			RandIndexQueue<Card> discard = new RandIndexQueue<Card>(originalShoeSize); //create discard pile with same capacity as shoe
			RandIndexQueue<Card> player = new RandIndexQueue<Card>(10); //10 cards is theoretical upper limit to how many cards. 4 aces, 4 twos and 2 threes give 18 and you cant hit after that
			RandIndexQueue<Card> dealer = new RandIndexQueue<Card>(10); //same upper limit as player
			
			for (int i = 0; i < numDecks; i++) //adds a full deck of cards on each iteration
			{
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Two)); //2 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Two)); //2 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Two)); //2 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Two)); //2 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Three)); //3 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Three)); //3 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Three)); //3 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Three)); //3 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Four)); //4 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Four)); //4 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Four)); //4 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Four)); //4 of hearts
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Five)); //5 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Five)); //5 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Five)); //5 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Five)); //5 of diamonds
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Six)); //6 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Six)); //6 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Six)); //6 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Six)); //6 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Seven)); //7 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Seven)); //7 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Seven)); //7 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Seven)); //7 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Eight)); //8 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Eight)); //8 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Eight)); //8 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Eight)); //8 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Nine)); //9 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Nine)); //9 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Nine)); //9 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Nine)); //9 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Ten)); //10 of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Ten)); //10 of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Ten)); //10 of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Ten)); //10 of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Jack)); //Jack of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Jack)); //Jack of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Jack)); //Jack of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Jack)); //Jack of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Queen)); //Queen of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Queen)); //Queen of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Queen)); //Queen of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Queen)); //Queen of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.King)); //King of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.King)); //King of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.King)); //King of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.King)); //King of hearts
				shoe.addItem(new Card(Card.Suits.Spades, Card.Ranks.Ace)); //Ace of spades
				shoe.addItem(new Card(Card.Suits.Clubs, Card.Ranks.Ace)); //Ace of clubs
				shoe.addItem(new Card(Card.Suits.Diamonds, Card.Ranks.Ace)); //Ace of diamonds
				shoe.addItem(new Card(Card.Suits.Hearts, Card.Ranks.Ace)); //Ace of hearts
			}
			
			shoe.shuffle();
			
			if (numRounds <= 10) //traces program if round is less than or equal to 10
			{
				for (int i = 0; i < numRounds; i++)
				{
					System.out.println("\n\nRound " + i + " beginning");
					player.addItem(shoe.removeItem()); //add 2 card to players hand from the shoe
					player.addItem(shoe.removeItem());
					dealer.addItem(shoe.removeItem()); //add 2 cards to dealers hand
					dealer.addItem(shoe.removeItem());
					
					playerValue = (player.get(0)).value() + (player.get(1)).value();
					dealerValue = (dealer.get(0)).value() + (dealer.get(1)).value();
					
					System.out.println("Player: Contents: " + player + "  : " + playerValue);
					System.out.println("Dealer: Contents: " + dealer + "  : " + dealerValue);
					
					while (playerValue < 17)
					{
						player.addItem(shoe.removeItem());
						if ((player.get(player.size() - 1)).value() == 11 && (playerValue + 11 > 21)) //will make the ace 1 if it will cause hand to bust
						{
							playerValue += (player.get(player.size() - 1)).value2();
						}
						else //adds card at its normal value
						{
							playerValue  = playerValue + (player.get(player.size() - 1)).value(); //adds new card value to playerValue
						}
						System.out.println("Player hits: " + player.get(player.size() - 1));
						
						playerSize = player.size();
							
						if (playerValue > 21 && !playerAceIsReduced) //will change first two aces if needs to.  will only do this once.
						{
							for (int j = 0; j < 2; j++)								
							{
								if ((player.get(j)).value() == 11)
								{
									playerValue = playerValue - 10; //if there is an ace and it makes the hand bust, change its value to 1.
									playerAceIsReduced = true;
								}
							} //end for
						}
					} //end while
					
					if (playerValue > 21)
					{
						System.out.println("Player BUSTS: Contents:  " + player + "  :  " + playerValue);
						System.out.println("Result: Dealer Wins!");
						dealerWins++;
						//dealer wins
					}
					else
					{
						System.out.println("Player STANDS: Contents: " + player + "  :  " + playerValue);
					
						while (dealerValue < 17)
						{
							dealer.addItem(shoe.removeItem());
							if ((dealer.get(dealer.size() - 1)).value() == 11 && (dealerValue + 11 > 21)) //will make the ace 1 if it will cause hand to bust
							{
								dealerValue += (dealer.get(dealer.size() - 1)).value2();
							}
							else //adds card at its normal value
							{
								dealerValue  = dealerValue + (dealer.get(dealer.size() - 1)).value(); //adds new card value to playerValue
							}
							System.out.println("Dealer hits: " + dealer.get(dealer.size() - 1));
							
							dealerSize = dealer.size();
							
							if (dealerValue > 21 && !dealerAceIsReduced) //will change first two aces if needs to.  will only do this once.
							{
								for (int j = 0; j < 2; j++)
								{
									if ((dealer.get(j)).value() == 11)
									{
										dealerValue = dealerValue - 10; //if there is an ace and it makes the hand bust, change its value to 1.
										dealerAceIsReduced = true;
									}
								} //end for
							}
						} //end while 
					
					if (dealerValue > 21)
					{
						System.out.println("Dealer BUSTS: Contents:  " + dealer + "  :  " + dealerValue);
						System.out.println("Result: Player Wins!");
						playerWins++; //player wins
					}
					else
					{
						System.out.println("Dealer STANDS: Contents: " + dealer + "  :  " + dealerValue);
						
						if (dealerValue > playerValue)
						{
							System.out.println("Result: Dealer Wins!");
							dealerWins++;
						}
						else if (dealerValue < playerValue)
						{
							System.out.println("Result: Player Wins!");
							playerWins++;
						}
						else
						{
							System.out.println("Result: PUSH!");
							pushes++;
						}
					}
				}
				
				playerSize = player.size();
				dealerSize = dealer.size();
				
				for (int j = 0; j < playerSize; j++)
				{
					discard.addItem(player.removeItem()); //puts players hand in discard pile
					playerValue = 0;
				}
				for (int k = 0; k < dealerSize; k++)
				{
					discard.addItem(dealer.removeItem()); //puts dealers hand in discard pile
					dealerValue = 0;
				}
				
				if (shoe.size() <= originalShoeSize/4.0)
				{
					discardSize = discard.size();
					for (int x = 0; x < discardSize; x++)
					{
						shoe.addItem(discard.removeItem());
					}
					shoe.shuffle();
				}
				
				
				}//end for
				
				System.out.println("\n\nAfter " + numRounds + " rounds, here are the results:");
				System.out.println("\tDealer Wins:  " + dealerWins);
				System.out.println("\tPlayer Wins:  " + playerWins);
				System.out.println("\tPushes:  " + pushes);
					
			}
			else
			{
				for (int i = 0; i < numRounds; i++)
				{
					player.addItem(shoe.removeItem()); //add 2 card to players hand from the shoe
					player.addItem(shoe.removeItem());
					dealer.addItem(shoe.removeItem()); //add 2 cards to dealers hand
					dealer.addItem(shoe.removeItem());
					
					playerValue = (player.get(0)).value() + (player.get(1)).value();
					dealerValue = (dealer.get(0)).value() + (dealer.get(1)).value();
					
					
					while (playerValue < 17)
					{
						player.addItem(shoe.removeItem());
						if ((player.get(player.size() - 1)).value() == 11 && (playerValue + 11 > 21)) //will make the ace 1 if it will cause hand to bust
						{
							playerValue += (player.get(player.size() - 1)).value2();
						}
						else //adds card at its normal value
						{
							playerValue  = playerValue + (player.get(player.size() - 1)).value(); //adds new card value to playerValue
						}
						
						playerSize = player.size();
							
						if (playerValue > 21 && !playerAceIsReduced) //will change first two aces if needs to.  will only do this once.
						{
							for (int j = 0; j < 2; j++)								
							{
								if ((player.get(j)).value() == 11)
								{
									playerValue = playerValue - 10; //if there is an ace and it makes the hand bust, change its value to 1.
									playerAceIsReduced = true;
								}
							} //end for
						}
					} //end while
					
					if (playerValue > 21)
					{
						dealerWins++;
						//dealer wins
					}
					else
					{
					
						while (dealerValue < 17)
						{
							dealer.addItem(shoe.removeItem());
							if ((dealer.get(dealer.size() - 1)).value() == 11 && (dealerValue + 11 > 21)) //will make the ace 1 if it will cause hand to bust
							{
								dealerValue += (dealer.get(dealer.size() - 1)).value2();
							}
							else //adds card at its normal value
							{
								dealerValue  = dealerValue + (dealer.get(dealer.size() - 1)).value(); //adds new card value to playerValue
							}
							
							dealerSize = dealer.size();
							
							if (dealerValue > 21 && !dealerAceIsReduced) //will change first two aces if needs to.  will only do this once.
							{
								for (int j = 0; j < 2; j++)
								{
									if ((dealer.get(j)).value() == 11)
									{
										dealerValue = dealerValue - 10; //if there is an ace and it makes the hand bust, change its value to 1.
										dealerAceIsReduced = true;
									}
								} //end for
							}
						} //end while 
					
					if (dealerValue > 21)
					{
						playerWins++; //player wins
					}
					else
					{
						
						if (dealerValue > playerValue)
						{
							dealerWins++;
						}
						else if (dealerValue < playerValue)
						{
							playerWins++;
						}
						else
						{
							pushes++;
						}
					}
				}
				
				playerSize = player.size();
				dealerSize = dealer.size();
				
				for (int j = 0; j < playerSize; j++)
				{
					discard.addItem(player.removeItem()); //puts players hand in discard pile
					playerValue = 0;
				}
				for (int k = 0; k < dealerSize; k++)
				{
					discard.addItem(dealer.removeItem()); //puts dealers hand in discard pile
					dealerValue = 0;
				}
				
				if (shoe.size() <= (originalShoeSize/4.0))
				{
					discardSize = discard.size();
					for (int n = 0; n < discardSize; n++)
					{
						Card c = discard.removeItem();
						shoe.addItem(c);
					}
					shoe.shuffle();
					System.out.println("\nReshuffling Shoe in Round " + i);
				}
				
				
				}//end for
				
				System.out.println("\n\nAfter " + numRounds + " rounds, here are the results:");
				System.out.println("\tDealer Wins:  " + dealerWins);
				System.out.println("\tPlayer Wins:  " + playerWins);
				System.out.println("\tPushes:  " + pushes);
			}
			
			
			
				
				
	}
}
