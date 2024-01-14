import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JOptionPane;
public class Game {
    private ArrayList<Player> players = new ArrayList<>(); // here are all players from the start of the game
    private ArrayList<Player> playersRemaining = new ArrayList<>(); // here are players who are playing current round (they still have cards)
    private int playersTurn = 0; // this int determines whose turn it is
    private int gamePhase; // this int determines what phase of the game is
    private int playersThatDidntCall; // this int determines how many players did not call who are in the queue before the player who bet
    private ArrayList <Player> winnersOfTheRound = new ArrayList<>(); // here all players that have won current round (there can be more than 1 player each round)
    private int winnersStrength; // this int determines winners strength of the cards that he won with
    private boolean hideCards = false; // this boolean is for hiding cards
    Table table = new Table();
    JFrame frame = new JFrame("Poker");
    JPanel panel = new JPanel() {
        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            int spaceBetweenCards = 1; // this is helpful int for displaying players cards at the end of the round
            if (gamePhase == 0) { // this phase is displaying each players cards
                for (int i = 0; i < table.getCardsOnTable().size(); i++) {
                    Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                    graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                }
                if (!hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource(playersRemaining.get(playersTurn).getCardsOnHand().get(i).getImageOfCard())).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                } else if (hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                }
            } else if (gamePhase == 1) { // this phase is displaying flop + each players cards
                for (int i = 0; i < 3; i++) {
                    Image cardImage = new ImageIcon(getClass().getResource(table.getCardsOnTable().get(i).getImageOfCard())).getImage();
                    graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                }
                for (int i = 3; i < table.getCardsOnTable().size(); i++) {
                    Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                    graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                }
                if (!hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource(playersRemaining.get(playersTurn).getCardsOnHand().get(i).getImageOfCard())).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                } else if (hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                }
            } else if (gamePhase == 2) { // this phase is displaying turn + each players cards
                for (int i = 0; i < 4; i++) {
                    Image cardImage = new ImageIcon(getClass().getResource(table.getCardsOnTable().get(i).getImageOfCard())).getImage();
                    graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                }
                for (int i = 4; i < table.getCardsOnTable().size(); i++) {
                    Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                    graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                }
                if (!hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource(playersRemaining.get(playersTurn).getCardsOnHand().get(i).getImageOfCard())).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                } else if (hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                }
            } else if (gamePhase == 3) { //this phase is displaying river + each players cards
                for (int i = 0; i < table.getCardsOnTable().size(); i++) {
                    Image cardImage = new ImageIcon(getClass().getResource(table.getCardsOnTable().get(i).getImageOfCard())).getImage();
                    graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                }
                if (!hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource(playersRemaining.get(playersTurn).getCardsOnHand().get(i).getImageOfCard())).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                } else if (hideCards) {
                    for (int i = 0; i < 2; i++) {
                        Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                    }
                }
            } else if (gamePhase == 4){ //this phase is displaying cards on table + every players cards + determines winner + remove players that lost all their chips
                for (int i = 0; i < table.getCardsOnTable().size(); i++) {
                    Image cardImage = new ImageIcon(getClass().getResource(table.getCardsOnTable().get(i).getImageOfCard())).getImage();
                    graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                }
                for(Player p: playersRemaining){
                    for (int j = 0; j<2; j++){
                        Image cardImage = new ImageIcon(getClass().getResource(p.getCardsOnHand().get(j).getImageOfCard())).getImage();
                        graphics.drawImage(cardImage, 150 * spaceBetweenCards + 125 * j, 600, 100, 140, null);
                    }
                    spaceBetweenCards+=2;
                }
                table.setPot(0);
                for (int i = 0; i<players.size();i++){
                    if (players.get(i).getChips() == 0){
                        players.remove(i);
                    }
                }
            }else if (gamePhase==5){ // this phase controls if there are more than 1 player and starting new round
                if (players.size() == 1){
                    gamePhase++;
                    panel.repaint();
                }else {
                    endOfGame();
                    startGame();
                    for (int i = 0; i < table.getCardsOnTable().size(); i++) {
                        Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                        graphics.drawImage(cardImage, 140 + 250 * i, 50, 200, 280, null);
                    }
                    if (!hideCards) {
                        for (int i = 0; i < 2; i++) {
                            Image cardImage = new ImageIcon(getClass().getResource(playersRemaining.get(playersTurn).getCardsOnHand().get(i).getImageOfCard())).getImage();
                            graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                        }
                    } else if (hideCards) {
                        for (int i = 0; i < 2; i++) {
                            Image cardImage = new ImageIcon(getClass().getResource("./Cards/BACKofCARD.png")).getImage();
                            graphics.drawImage(cardImage, 140 + 250 * i, 500, 200, 280, null);
                        }
                    }
                }
            }else { // this is last phase of full game happens only if there is 1 player with all chips
                JOptionPane.showMessageDialog(null, "CONGRATULATIONS!\n" + players.get(0) + " is the WINNER! ");
            }
        }
    };
    JPanel buttonPanel = new JPanel();
    JLabel chipLabel = new JLabel();
    JButton foldButton = new JButton("Fold");
    JButton betButton = new JButton("Bet");
    JButton checkcallButton = new JButton("Chcek/Call");
    JButton skipButton = new JButton("Skip");
    public Game() throws Exception { // here is happening all the procces of the game
        int numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of players"));
        if (numberOfPlayers <= 1) {
            throw new Exception("A game of poker should be played by at least 2 players");
        }
        int chipsPerOne = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of chips for one player"));
        if (chipsPerOne <= 0) {
            throw new Exception("Every player must have at least 1 chip at the start of the game ");
        }
        for (int i = 1; i < numberOfPlayers + 1; i++) { // cycle is creating all players
            String name = JOptionPane.showInputDialog("Enter name of Player" + i);
            if (name.matches("^[A-Z]{1}[a-z]+[0-9]*$")) {
                if (i == 1) {
                    players.add(new Player(name, chipsPerOne));
                } else {
                    for (int j = 0; j < numberOfPlayers; j++) {
                        if (name.equals(players.get(j).getName())) {
                            throw new Exception("Name: " + name + " is already taken");
                        } else {
                            players.add(new Player(name, chipsPerOne));
                            j = numberOfPlayers;
                        }
                    }
                }
            } else {
                throw new Exception("Name: " + name + " is not written correctly");
            }
        }
        JOptionPane.showMessageDialog(null, "After clicking OK, First's cards will show up, \n make sure other players won't see them.");
        startGame(); // here is starting the whole game and setting everything up

        frame.setVisible(true);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(34, 139, 34));
        frame.add(panel);
        betButton.setFocusable(false);
        buttonPanel.add(betButton);
        checkcallButton.setFocusable(false);
        buttonPanel.add(checkcallButton);
        foldButton.setFocusable(false);
        buttonPanel.add(foldButton);
        skipButton.setFocusable(false);
        buttonPanel.add(skipButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        chipLabel.setText(playersRemaining.get(playersTurn) + "          Your Chips: " + playersRemaining.get(playersTurn).getChips() + "          Pot: " + table.getPot());
        frame.add(chipLabel, BorderLayout.NORTH);
        playersThatDidntCall = -2;
        table.setHighestBidder(players.get(0));

        foldButton.addActionListener(new ActionListener() { // here is everthing what is fold button doing
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gamePhase < 4) { // this button works only when game phase is below 4
                    playersRemaining.remove(playersTurn); // it removes the player from playersRemaining arraylist because he will no longer play current round
                    playersThatDidntCall--;
                    if (playersRemaining.size() == 1) { // if after current player fold his cards is only 1 player remaining it will set up evertything for gamephase 4
                        gamePhase = 3;
                        playersTurn = 0;
                        table.getHighestBidder().setChips(table.getHighestBidder().getChips() + table.getHighestBid());
                        table.setPot(table.getPot() - table.getHighestBid());
                        table.setHighestBid(0);
                    } else if (playersTurn == playersRemaining.size()) { // if everyone did something in current gamephase and everyone called if someone bet it will set up evertything for next gamephase
                        playersTurn = 0;
                        playersThatDidntCall++;
                        if (playersThatDidntCall < 0) {
                            gamePhase++;
                            playersThatDidntCall--;
                            table.setHighestBid(0);
                        }
                    }
                    int resetHighestBid = -1;
                    if (playersTurn == playersThatDidntCall) { // this if controls that every player calls bet from someone and if yes it will set up evertything for next gamephase
                        gamePhase++;
                        resetHighestBid = playersTurn;
                        playersTurn = 0;
                    }
                    hideCards = true;
                    panel.repaint();
                    if (gamePhase <= 3) {
                        JOptionPane.showMessageDialog(null, "You folded your cards " + "\n It's " + playersRemaining.get(playersTurn) + "'s turn");
                    }else if (gamePhase == 4){
                        for (Player p : playersRemaining) {
                            if (p.handEvaluation() > winnersStrength) {
                                winnersStrength = p.handEvaluation();
                                winnersOfTheRound.clear();
                                winnersOfTheRound.add(p);
                            }else if (p.handEvaluation() == winnersStrength) {
                                winnersOfTheRound.add(p);
                            }
                        }
                        for (Player p : winnersOfTheRound) {
                            p.setChips(p.getChips() + (table.getPot() / winnersOfTheRound.size()));
                        }
                        JOptionPane.showMessageDialog(null, "SHOWDOWN! \n Player(s): " + winnersOfTheRound + " have won this round. \n Every winner of this round have won " + table.getPot() / winnersOfTheRound.size() + " chips. \n Click the SKIP button to start next round");
                    }
                    hideCards = false;
                    if (resetHighestBid == playersThatDidntCall) { // this if resets highest bid and players that didnt call integer
                        table.setHighestBid(0);
                        playersThatDidntCall = -2;
                    }
                    panel.repaint();
                    chipLabel.setText(playersRemaining.get(playersTurn) + "          Your Chips: " + playersRemaining.get(playersTurn).getChips() + "          Pot: " + table.getPot() + "          Highest Bid: " + table.getHighestBid());
                    frame.repaint();
                }
            }
        });
        checkcallButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gamePhase < 4) { // this button works only when game phase is below 4
                    if (playersRemaining.get(playersTurn).getChips() > table.getHighestBid()) { // this if controls if player have enough chips for call
                        table.setPot(table.getPot() + table.getHighestBid());
                        playersRemaining.get(playersTurn).setChips(playersRemaining.get(playersTurn).getChips() - table.getHighestBid());
                    } else if (playersRemaining.get(playersTurn).getChips() <= table.getHighestBid() && playersRemaining.get(playersTurn).getChips() > 0) { // if no it will bet all the players chips
                        table.setPot(table.getPot() + playersRemaining.get(playersTurn).getChips());
                        playersRemaining.get(playersTurn).setChips(0);
                        JOptionPane.showMessageDialog(null, "ALL IN!");
                    }
                    playersTurn++;
                    if (playersTurn >= playersRemaining.size()) {
                        playersTurn = 0;
                        if (playersThatDidntCall < 0) {
                            gamePhase++;
                        }
                    }
                    int resetHighestBid = -1;
                    if (playersTurn == playersThatDidntCall) { // this if controls that every player calls bet from someone and if yes it will set up evertything for next gamephase
                        gamePhase++;
                        resetHighestBid = playersTurn;
                        playersTurn = 0;
                    }
                    hideCards = true;
                    panel.repaint();
                    if (gamePhase <= 3) {
                        JOptionPane.showMessageDialog(null, "You chceked/called : " + table.getHighestBid() + "\n It's " + playersRemaining.get(playersTurn) + "'s turn");
                    }else if (gamePhase == 4){ // if gamephase is 4 this determines the winner
                        for (Player p : playersRemaining) {
                            if (p.handEvaluation() > winnersStrength) {
                                winnersStrength = p.handEvaluation();
                                winnersOfTheRound.clear();
                                winnersOfTheRound.add(p);
                            } else if (p.handEvaluation() == winnersStrength) {
                                winnersOfTheRound.add(p);
                            }
                        }
                        for (Player p : winnersOfTheRound) {
                            p.setChips(p.getChips() + (table.getPot() / winnersOfTheRound.size()));
                        }
                        JOptionPane.showMessageDialog(null, "SHOWDOWN! \n Player(s): " + winnersOfTheRound + " have won this round. \n Every winner of this round have won " + table.getPot() / winnersOfTheRound.size() + " chips. \n Click the SKIP button to start next round");
                    }
                    hideCards = false;
                    if (resetHighestBid == playersThatDidntCall) { // this if resets highest bid and players that didnt call integer
                        table.setHighestBid(0);
                        playersThatDidntCall = -2;
                    }
                    panel.repaint();
                    chipLabel.setText(playersRemaining.get(playersTurn) + "          Your Chips: " + playersRemaining.get(playersTurn).getChips() + "          Pot: " + table.getPot() + "          Highest Bid: " + table.getHighestBid());
                    frame.repaint();
                }
            }
        });
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gamePhase < 4) { // this button works only when game phase is below 4
                    int playersBet = 0; // this int is how much player bet
                    boolean correctBet = false; // this boolean controls that the player write his bet right
                    playersThatDidntCall = playersTurn;

                    do {
                        try {
                            playersBet = Integer.parseInt(JOptionPane.showInputDialog("Enter the value of chips you want to bet"));
                            correctBet = true;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Enter number!");
                        }
                    } while (!correctBet);
                    if (playersBet >= playersRemaining.get(playersTurn).getChips()) { // this controls that the player have enough chips for bet that he write
                        playersBet = playersRemaining.get(playersTurn).getChips();
                        JOptionPane.showMessageDialog(null, "ALL IN!");
                    } else if (playersBet < table.getHighestBid()) { // this controls that the player bet at least 1 chip more than its highest bid
                        playersBet = table.getHighestBid() + 1;
                        table.setHighestBid(table.getHighestBid() + 1);
                    }
                    table.setPot(table.getPot() + playersBet);
                    playersRemaining.get(playersTurn).setChips(playersRemaining.get(playersTurn).getChips() - playersBet);
                    if (playersBet > table.getHighestBid()) { // this sets up players bet for highest bid
                        table.getHighestBidder().setChips(table.getHighestBidder().getChips() + table.getHighestBid());
                        table.setPot(table.getPot() - table.getHighestBid());
                        table.setHighestBid(playersBet);
                        table.setHighestBidder(playersRemaining.get(playersTurn));
                    }
                    playersTurn++;
                    if (playersTurn == playersRemaining.size()) {
                        playersTurn = 0;
                    }
                    hideCards = true;
                    panel.repaint();
                    JOptionPane.showMessageDialog(null, "You bet: " + playersBet + "\n It's " + playersRemaining.get(playersTurn) + "'s turn");
                    hideCards = false;
                    panel.repaint();
                    chipLabel.setText(playersRemaining.get(playersTurn) + "          Your Chips: " + playersRemaining.get(playersTurn).getChips() + "          Pot: " + table.getPot() + "          Highest Bid: " + table.getHighestBid());
                    frame.repaint();
                }
            }
        });
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // it sets everything for next round
                if (gamePhase==4){ // this button works only when game phase is 4
                    gamePhase++;
                    JOptionPane.showMessageDialog(null, "After clicking OK, First's cards will show up, \n make sure other players won't see them.");
                    chipLabel.setText(players.get(0) + "          Your Chips: " + players.get(0).getChips() + "          Pot: " + table.getPot() + "          Highest Bid: " + table.getHighestBid());
                    panel.repaint();
                    frame.repaint();
                }
            }
        });
    }
    public void startGame() { // this method shuffle deck and deal cards to each player
        Deck deck = new Deck();
        deck.shufleCards();
        for (int i = 0; i < 5; i++) {
            table.addCard(deck.getDeckOfCards().remove(deck.getDeckOfCards().size() - 1));
        }
        for (Player p : players) {
            for (int i = 0; i < 2; i++) {
                p.addHandCard(deck.getDeckOfCards().remove(deck.getDeckOfCards().size() - 1));
            }
            for (int i = 0; i < 2; i++) {
                p.addAllCards(p.getCardsOnHand().get(i));
            }
            for (int i = 0; i < 5; i++) {
                p.addAllCards(table.getCardsOnTable().get(i));
            }
            playersRemaining.add(p);
            table.setPot(0);
            table.setHighestBid(0);
            gamePhase = 0;
            panel.repaint();
        }
    }
    public void endOfGame(){ // this metohd is clearing everything to ready for start new round
        table.getCardsOnTable().clear();
        for (Player p : players) {
            p.getCardsOnHand().clear();
            p.getCardsToPlayWith().clear();
        }
        playersRemaining.clear();
        winnersStrength = 0;
        winnersOfTheRound.clear();
    }
}