package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class TestGameManager {

    GameManager gameManager;

    @BeforeEach
    public void setupGameManager() {
        gameManager = new GameManager();
        try {
            gameManager.loadGame(new File("test-files/test.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }
    }

    @Test
    public void testLoadGame(){
        GameManager testLoadGame = gameManager;

        assertEquals("1 | Criança | Zombie | Melanie | -0 @ (3, 3)",testLoadGame.getCreatureInfoAsString(1));
        assertEquals("-4 | Lixívia @ (4, 4) | 1.0 litros",testLoadGame.getEquipmentInfoAsString(-4));
        assertEquals("-3 | Pistola Walther PPK @ (2, 1) | 3 balas",testLoadGame.getEquipmentInfoAsString(-3));
        assertEquals("SH",testLoadGame.getSquareInfo(6,0));
    }
    @Test
    public void testGetSurvivors(){
        GameManager testGetSurvivors = gameManager;
        try{
            testGetSurvivors.loadGame(new File("test-files/test.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }
        ArrayList<String> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add("Nr. de turnos terminados:");
        resultadoEsperado.add("0");
        resultadoEsperado.add("");
        resultadoEsperado.add("OS VIVOS");
        resultadoEsperado.add("6 Karate Kid");
        resultadoEsperado.add("7 Freddie M.");
        resultadoEsperado.add("8 James Bond");
        resultadoEsperado.add("9 John Wayne");
        resultadoEsperado.add("10 Max");
        resultadoEsperado.add("");
        resultadoEsperado.add("OS OUTROS");
        resultadoEsperado.add("1 (antigamente conhecido como Melanie)");
        resultadoEsperado.add("2 (antigamente conhecido como Walker)");
        resultadoEsperado.add("3 (antigamente conhecido como Frankenstein)");
        resultadoEsperado.add("4 (antigamente conhecido como Crawler)");
        resultadoEsperado.add("5 (antigamente conhecido como Babe)");
        resultadoEsperado.add("-----");

        assertEquals(resultadoEsperado,testGetSurvivors.getSurvivors());




    }
    @Test
    public void testKill(){
        GameManager testKill = gameManager;
        try{
            testKill.loadGame(new File("test-files/test.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }
        testKill.move(3,3,2,3);
        testKill.move(4,3,2,1);
        testKill.move(2,3,3,3);
        testKill.move(2,1,1,1);
        assertEquals("H:7",testKill.getSquareInfo(1,1));

    }
    @Test
    public void testEndGame(){
        GameManager testEndGame = gameManager;

        testEndGame.move(1,1,1,0);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(4,3,2,1);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(4,5,5,4);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(2,1,0,1);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(1,0,1,1);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(0,1,1,1);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(3,3,3,2);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(1,1,2,0);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(3,2,3,1);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(2,0,3,1);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(5,3,4,2);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(3,1,4,2);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(5,4,4,3);
        assertFalse(testEndGame.gameIsOver());
        testEndGame.move(4,2,4,3);
        assertTrue(testEndGame.gameIsOver());

    }
    @Test
    public void testEndGame2(){
        GameManager testEndGame2 = gameManager;
        testEndGame2.move(3,3,3,2);
        assertFalse(testEndGame2.gameIsOver());
        testEndGame2.move(4,3,4,2);
        assertFalse(testEndGame2.gameIsOver());
        testEndGame2.move(3,2,3,3);
        assertFalse(testEndGame2.gameIsOver());
        testEndGame2.move(4,2,4,3);
        assertFalse(testEndGame2.gameIsOver());
        testEndGame2.move(3,3,3,2);
        assertFalse(testEndGame2.gameIsOver());
        testEndGame2.move(4,3,4,2);
        assertFalse(testEndGame2.gameIsOver());
        testEndGame2.move(3,2,3,3);
        assertFalse(testEndGame2.gameIsOver());
        testEndGame2.move(4,2,4,3);
        assertTrue(testEndGame2.gameIsOver());

    }
    @Test
    public void testEndGame3(){
        GameManager testEndGame3 = gameManager;
        testEndGame3.move(3,3,3,4);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(4,3,4,2);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(3,3,3,2);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(4,2,4,3);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(3,2,3,3);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(4,3,4,2);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(3,3,3,2);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(4,2,4,3);
        assertFalse(testEndGame3.gameIsOver());
        testEndGame3.move(3,2,3,3);
        assertTrue(testEndGame3.gameIsOver());


    }
    @Test
    public void testEndGame4(){
       GameManager testEndGame4 = gameManager;
       testEndGame4.move(3,3,3,2);
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(4,3,2,1);
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(2,2,2,3);   //jogada invalida
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,2,3,1);
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(2,1,0,1);  //Kill
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,1,3,0);  //1
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,0,3,1);  //jogada invalida
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,4,3,3);  //2
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,0,3,1);  //3
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,3,3,2);  //4
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,1,4,1);  //5
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,2,3,1);  //6
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,1,3,2);  //jogada invalida
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(4,1,4,0);  //7
       assertFalse(testEndGame4.gameIsOver());
       testEndGame4.move(3,1,3,2);  //8
       assertTrue(testEndGame4.gameIsOver());


    }
    @Test
    public void testEndGame5(){
       GameManager testEndGame5 = gameManager;
       testEndGame5.move(3,3,3,4);  //INFECT
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(2,2,4,2);  //MOVE
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(3,4,4,4);  //WEAPON
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(4,2,4,0); //MOVE
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(4,5,5,6); //INFECT
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(4,0,6,0); //SAFEHEAVEN
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(5,3,4,3); //INFECT
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(6,5,6,3); //WEAPON
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(1,1,2,1); //WEAPON
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(2,1,3,1); //INVALID
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(6,3,6,1); //MOVE
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(4,1,4,0); //INVALID
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(6,1,6,0); //INVALID
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(2,1,2,0); //WEAPON
       assertFalse(testEndGame5.gameIsOver());
       testEndGame5.move(6,1,6,0); //SAFEHEAVEN
       assertTrue(testEndGame5.gameIsOver());

        ArrayList<String> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add("Nr. de turnos terminados:");
        resultadoEsperado.add("12");
        resultadoEsperado.add("");
        resultadoEsperado.add("OS VIVOS");
        resultadoEsperado.add("9 John Wayne");
        resultadoEsperado.add("10 Max");
        resultadoEsperado.add("");
        resultadoEsperado.add("OS OUTROS");
        resultadoEsperado.add("1 (antigamente conhecido como Melanie)");
        resultadoEsperado.add("2 (antigamente conhecido como Walker)");
        resultadoEsperado.add("3 (antigamente conhecido como Frankenstein)");
        resultadoEsperado.add("4 (antigamente conhecido como Crawler)");
        resultadoEsperado.add("5 (antigamente conhecido como Babe)");
        resultadoEsperado.add("6 (antigamente conhecido como Karate Kid)");
        resultadoEsperado.add("7 (antigamente conhecido como Freddie M.)");
        resultadoEsperado.add("8 (antigamente conhecido como James Bond)");
        resultadoEsperado.add("-----");
        assertEquals(resultadoEsperado,testEndGame5.getSurvivors());





    }
    @Test
    public void testEndGame6(){
        GameManager testEndGame6 = gameManager;
        testEndGame6.move(3,3,3,2); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(3,4,4,4); //WEAPON
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(0,1,0,2); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(4,3,4,2); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(1,1,1,0); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(6,5,6,3); //WEAPON
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(5,3,4,2); //INFECT
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(4,4,4,3); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(4,2,4,3); //DEFEND
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(2,2,2,4); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(4,5,3,6); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(4,3,4,4); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(5,3,6,3); //DEFEND
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(2,4,0,4); //MOVE
        assertFalse(testEndGame6.gameIsOver());
        testEndGame6.move(3,2,3,1);
        assertTrue(testEndGame6.gameIsOver());



    }
    @Test
    public void childWithEquipment(){
        GameManager childWithEquipment = gameManager;

        childWithEquipment.move(1,1,1,0);
        childWithEquipment.move(3,4,4,4); //criança apanha lixívia
        assertTrue(childWithEquipment.hasEquipment(6,3));
        assertEquals("6 | Criança | Humano | Karate Kid | +1 @ (4, 4) | -4 | Lixívia @ (4, 4) | 1.0 litros",
                childWithEquipment.getCreatureInfoAsString(6));
        assertEquals(null,childWithEquipment.getEquipmentInfoAsString(-4));
        childWithEquipment.move(5,3,4,4); //criança é atacada
        childWithEquipment.move(4,4,3,4); //criança move-se
        assertTrue(childWithEquipment.hasEquipment(6,3));
        assertEquals("6 | Criança | Humano | Karate Kid | +1 @ (3, 4) | -4 | Lixívia @ (3, 4) | 0.7 litros",
                childWithEquipment.getCreatureInfoAsString(6));
        childWithEquipment.move(3,3,3,4); //criança é atacada
        assertTrue(childWithEquipment.hasEquipment(6,3));
        assertEquals("6 | Criança | Humano | Karate Kid | +1 @ (3, 4) | -4 | Lixívia @ (3, 4) | 0.4 litros",
                childWithEquipment.getCreatureInfoAsString(6));
        childWithEquipment.move(3,4,2,4);
        childWithEquipment.move(3,3,2,3);
        childWithEquipment.move(4,3,4,1);
    }
}
