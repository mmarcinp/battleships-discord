package pl.mmarcinp.bsbot.game;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Board {
    private int numRows = 10;
    private int numColumns = 10;
    private int numShips = 5;
    private int playerShips = 5;
    private int botShips = 5;
    private List<List<String>> shipsXy;
    private List<List<String>> botShipsXy;

    public Board() {
        shipsXy = initBotList();
        botShipsXy = initBotList();
    }

    private List<List<String>> initList() {
        shipsXy = new ArrayList<>();
        for(int i = 0;i<numRows;i++) {
            List<String> innerList = new ArrayList<>();
            for(int j = 0;j<numRows;j++) {
                innerList.add("O");
            }
            shipsXy.add(innerList);
        }
        return shipsXy;
    }

    private List<List<String>> initBotList() {
        shipsXy = new ArrayList<>();
        for(int i = 0;i<numRows;i++) {
            List<String> innerList = new ArrayList<>();
            for(int j = 0;j<numRows;j++) {
                if((j==2 || j==3) && i==6) {//#2
                    innerList.add("S ");
                }
                else if((j==9) && (i>6 && i<10)) {//#3.1
                    innerList.add("S ");
                }
                else if((j==0) && (i>3 && i<7)) {//#3.2
                    innerList.add("S ");
                }
                else if(j <= 4 && i == 0) {//#5
                    innerList.add("S ");
                }
                else if((j>=3 && j<=6) && (i==9)) {//#4
                    innerList.add("S ");
                } else {
                    innerList.add("O");
                }
            }
            shipsXy.add(innerList);
        }
        return shipsXy;
    }

    public String display() {
        String result = displaySingleBoard(shipsXy);
        result += "--------------------------\n";
        result += displaySingleBoard(botShipsXy);
        return result;
    }

    private String displaySingleBoard(List<List<String>> shipsXy) {
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<numRows;i++) {
            for(int j = 0;j<numRows;j++) {
                result.append(shipsXy.get(i).get(j));
                result.append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public void shoot(Xy xy) {
        String place = botShipsXy.get(xy.getX()).get(xy.getY());
        if(place.contains("S")) {
            place = "H ";
        } else {
            place = "M ";
        }
        botShipsXy.get(xy.getX()).set(xy.getY(), place);
    }
}
