/*******************************************
 * Author: Fabiano Góes - 13/12/2018
 * Util functions to help about Collections
 *******************************************/

 export class CollectionUtils {

  public static shuffle(array) {
    let indice_atual = array.length, valor_temporario, indice_aleatorio;
    while (0 !== indice_atual) {
      indice_aleatorio = Math.floor(Math.random() * indice_atual);
      indice_atual -= 1;
      valor_temporario = array[indice_atual];
      array[indice_atual] = array[indice_aleatorio];
      array[indice_aleatorio] = valor_temporario;
    }
    return array;
  }

}
