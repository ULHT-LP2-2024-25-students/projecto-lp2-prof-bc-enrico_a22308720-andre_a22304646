package pt.ulusofona.lp2.thenightofthelivingdeisi;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

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

        assertEquals("1 | Criança | Zombie | Melanie | -0 @(3,3)",gameManager.getCreatureInfoAsString(1));
        assertEquals("-4 | Lixívia @(4,4) | 1.0 litros",gameManager.getEquipmentInfoAsString(-4));
        assertEquals("-3 | Pistola Walter PPK @(2,1) | 3 balas",gameManager.getEquipmentInfoAsString(-3));
        assertEquals("SH",gameManager.getSquareInfo(6,0));
    }
}
