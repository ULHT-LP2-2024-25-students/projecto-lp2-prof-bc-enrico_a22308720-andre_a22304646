//package pt.ulusofona.lp2.thenightofthelivingdeisi;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
//public class TestGameManager {
//
//
//    @Test
//    public void testLoadGame(){
//        GameManager gameManager = new GameManager();
//        try{
//            gameManager.loadGame(new File("test-files/test.txt"));
//        }catch (FileNotFoundException e){
//            System.out.println("Ficheiro não existe");
//        } catch (InvalidFileException e) {
//            System.out.println("Ficheiro inválido");
//        }
//
//        assertEquals("1 | Criança | Zombie | Melanie | -0 @(3,3)",gameManager.getCreatureInfoAsString(1));
//        assertEquals("-4 | Lixívia @(4,4) | 1.0 litros",gameManager.getEquipmentInfoAsString(-4));
//        assertEquals("-3 | Pistola Walter PPK @(2,1) | 3 balas",gameManager.getEquipmentInfoAsString(-3));
//        assertEquals("SH",gameManager.getSquareInfo(6,0));
//    }
//    @Test
//    public void testGetSurvivors(){
//        GameManager gameManager = new GameManager();
//        try{
//            gameManager.loadGame(new File("test-files/test.txt"));
//        }catch (FileNotFoundException e){
//            System.out.println("Ficheiro não existe");
//        } catch (InvalidFileException e) {
//            System.out.println("Ficheiro inválido");
//        }
//        ArrayList<String> resultadoEsperado = new ArrayList<>();
//        resultadoEsperado.add("Nr. de turnos terminados:");
//        resultadoEsperado.add("0");
//        resultadoEsperado.add("");
//        resultadoEsperado.add("OS VIVOS");
//        resultadoEsperado.add("6 Karate Kid");
//        resultadoEsperado.add("7 Freddie M.");
//        resultadoEsperado.add("8 James Bond");
//        resultadoEsperado.add("9 John Wayne");
//        resultadoEsperado.add("10 Max");
//        resultadoEsperado.add("");
//        resultadoEsperado.add("OS OUTROS");
//        resultadoEsperado.add("1 (antigamente conhecido como Melanie)");
//        resultadoEsperado.add("2 (antigamente conhecido como Walker)");
//        resultadoEsperado.add("3 (antigamente conhecido como Frankenstein)");
//        resultadoEsperado.add("4 (antigamente conhecido como Crawler)");
//        resultadoEsperado.add("5 (antigamente conhecido como Babe)");
//        resultadoEsperado.add("-----");
//
//        assertEquals(resultadoEsperado,gameManager.getSurvivors());
//
//
//
//
//    }
//}
