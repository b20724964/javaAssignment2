
//The aim of the program is to develop a simple Movie Database System similar to IMDB.
//The program includes the languages, duration, authors, actors and so on of the movies.
//Users can also vote movies.
//The program can list movies and actors according to some criteria.
public class Main {
    public static void main(String[] args) {
        //read people.txt and split values for each object
        Peoples peoples = new Peoples(args[0]);
        //read films.txt and split values for each object
        Films films = new Films(args[1]);
        //read commands.txt and create a string 2D array(result)
        //Commands class has some methods which travel over the result and execute operations in the relevant commands.
        Commands commands = new Commands(args[2]);
        commands.createOutputFile(args[3]); 
        for (int i = 0; i < commands.result.length; i++) {
            if (commands.result[i][0].equals("RATE")) {
                commands.rate(films, peoples, commands.result[i][1], commands.result[i][2], commands.result[i][3]);
            } else if (commands.result[i][0].equals("ADD")) {
                commands.addFeatureFilm(films, peoples, commands.result[i][2], commands.result[i][3],
                        commands.result[i][4], commands.result[i][5], commands.result[i][6], commands.result[i][7],
                        commands.result[i][8], commands.result[i][9], commands.result[i][10], commands.result[i][11],
                        commands.result[i][12]);
            } else if (commands.result[i][0].equals("LIST") && commands.result[i][1].equals("USER")) {
                commands.listUserRates(films, peoples, commands.result[i][2]);
            } else if (commands.result[i][0].equals("VIEWFILM")) {
                commands.viewFilm(films, peoples, commands.result[i][1]);
            } else if (commands.result[i][0].equals("EDIT")) {
                commands.editRate(films, peoples, commands.result[i][2], commands.result[i][3], commands.result[i][4]);
            } else if (commands.result[i][0].equals("REMOVE")) {
                commands.removeRate(films, peoples, commands.result[i][2], commands.result[i][3]);
            } else if (commands.result[i][0].equals("LIST") && commands.result[i][1].equals("FILM")) {
                commands.listTVSeries(films);
            } else if (commands.result[i][0].equals("LIST") && commands.result[i][3].equals("COUNTRY")) {
                commands.listFilmsByCountry(films, commands.result[i][4]);
            } else if (commands.result[i][0].equals("LIST") && commands.result[i][2].equals("BEFORE")) {
                commands.listFilmsBeforeYear(films, commands.result[i][1], commands.result[i][3]);
            } else if (commands.result[i][0].equals("LIST") && commands.result[i][2].equals("AFTER")) {
                commands.listFilmsAfterYear(films, commands.result[i][1], commands.result[i][3]);
            } else if (commands.result[i][0].equals("LIST") && commands.result[i][3].equals("RATE")) {
                commands.listFilmsRate(films);
            } else if (commands.result[i][0].equals("LIST") && commands.result[i][1].equals("ARTISTS")) {
                commands.listArtistByCountry(peoples, commands.result[i][3]);
            }
        }
        commands.closeOutputFile();


    }

}
