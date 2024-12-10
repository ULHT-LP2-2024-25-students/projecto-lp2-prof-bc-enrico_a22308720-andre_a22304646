package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;



public class TestGameManager {
    @Test
    public void testLoadGame(){
        GameManager gameManager = new GameManager();
        try{
            gameManager.loadGame(new File("test-files/test.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }

        assertEquals("1 | Criança | Zombie | Melanie | -0 @ (3, 3)",gameManager.getCreatureInfoAsString(1));
        assertEquals("-4 | Lixívia @ (4, 4) | 1.0 litros",gameManager.getEquipmentInfoAsString(-4));
        assertEquals("-3 | Pistola Walther PPK @ (2, 1) | 3 balas",gameManager.getEquipmentInfoAsString(-3));
        assertEquals("SH",gameManager.getSquareInfo(6,0));
    }
    @Test
    public void testGetSurvivors(){
        GameManager gameManager = new GameManager();
        try{
            gameManager.loadGame(new File("test-files/test.txt"));
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

        assertEquals(resultadoEsperado,gameManager.getSurvivors());




    }
    @Test
    public void testKill(){
        GameManager gameManager = new GameManager();
        try{
            gameManager.loadGame(new File("test-files/test.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }
        gameManager.move(3,3,2,3);
        gameManager.move(4,3,2,1);
        gameManager.move(2,3,3,3);
        gameManager.move(2,1,1,1);
        assertEquals("H:7",gameManager.getSquareInfo(1,1));

    }
    @Test
    public void testEndGame(){
        GameManager gameManager = new GameManager();
        try{
            gameManager.loadGame(new File("test-files/test.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }
        gameManager.move(1,1,1,0);
        gameManager.move(4,3,2,1);
        gameManager.move(4,5,5,4);
        gameManager.move(2,1,0,1);
        gameManager.move(1,0,1,1);
        gameManager.move(0,1,1,1);
        gameManager.move(3,3,3,2);
        gameManager.move(1,1,2,0);
        gameManager.move(3,2,3,1);
        gameManager.move(2,0,3,1);
        gameManager.move(5,3,4,2);
        gameManager.move(3,1,4,2);
        gameManager.move(5,4,4,3);
        gameManager.move(4,2,4,3);
        assertTrue(gameManager.gameIsOver());

    }
    @Test
    public void testEndGame2(){
        GameManager gameManager = new GameManager();
        try{
            gameManager.loadGame(new File("test-files/test.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }
        gameManager.move(3,3,3,2);
        assertFalse(gameManager.gameIsOver());
        gameManager.move(4,3,4,2);
        assertFalse(gameManager.gameIsOver());
        gameManager.move(3,2,3,3);
        assertFalse(gameManager.gameIsOver());
        gameManager.move(4,2,4,3);
        assertFalse(gameManager.gameIsOver());
        gameManager.move(3,3,3,2);
        assertFalse(gameManager.gameIsOver());
        gameManager.move(4,3,4,2);
        assertFalse(gameManager.gameIsOver());
        gameManager.move(3,2,3,3);
        assertFalse(gameManager.gameIsOver());
        gameManager.move(4,2,4,3);
        assertTrue(gameManager.gameIsOver());




    }
    @Test
    public void childWithEquipment(){
        GameManager gameManager = new GameManager();
        try{
            gameManager.loadGame(new File("test-files/test.txt"));
        }catch (FileNotFoundException e){
            System.out.println("Ficheiro não existe");
        } catch (InvalidFileException e) {
            System.out.println("Ficheiro inválido");
        }

        gameManager.move(1,1,1,0);
        gameManager.move(3,4,4,4); //criança apanha lixívia
        assertTrue(gameManager.hasEquipment(6,3));
        assertEquals("6 | Criança | Humano | Karate Kid | +1 @ (4, 4) | -4 | Lixívia @ (4, 4) | 1.0 litros",
                gameManager.getCreatureInfoAsString(6));
        assertEquals(null,gameManager.getEquipmentInfoAsString(-4));
        gameManager.move(5,3,4,4); //criança é atacada
        gameManager.move(4,4,3,4); //criança move-se
        assertTrue(gameManager.hasEquipment(6,3));
        assertEquals("6 | Criança | Humano | Karate Kid | +1 @ (3, 4) | -4 | Lixívia @ (3, 4) | 0.7 litros",
                gameManager.getCreatureInfoAsString(6));
        gameManager.move(3,3,3,4); //criança é atacada
        assertTrue(gameManager.hasEquipment(6,3));
        assertEquals("6 | Criança | Humano | Karate Kid | +1 @ (3, 4) | -4 | Lixívia @ (3, 4) | 0.4 litros",
                gameManager.getCreatureInfoAsString(6));
        gameManager.move(3,4,2,4);
        gameManager.move(3,3,2,3);
        gameManager.move(4,3,4,1);





    }
}
