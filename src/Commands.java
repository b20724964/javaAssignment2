

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Commands implements Reader {
    String[][] result;
    private PrintWriter writer;

    public Commands(String stringFile) {
        this.result = FileReader(stringFile);

    }

    public void createOutputFile(String fileName) {
        try {
            this.writer = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeOutputFile() {
        writer.close();
    }

    private String findFilm(ArrayList<String> films, String id) {
        for (int i = 1; i < films.size(); i += 2) {
            if (films.get(i).equals(id)) {
                return films.get(i - 1);
            }
        }
        return "";
    }

    private String findPerson(ArrayList<String> person, String id) {
        for (int i = 1; i < person.size(); i += 2) {
            if (person.get(i).equals(id)) {
                return person.get(i - 1);
            }
        }
        return "";
    }

    public void rate(Films films, Peoples peoples, String userId, String filmId, String ratingPoint) {
        writer.println("RATE\t" + userId + " " + filmId + " " + ratingPoint + "\n");
        String filmCategory = findFilm(films.allFilmsId, filmId);
        String personCategory = findPerson(peoples.allPeoplesId, userId);
        boolean control = true;
        for (User user : peoples.users) {
            if (user.getId() == Integer.parseInt(userId)) {
                for (int filmid : user.getRateList()) {
                    if (filmid == Integer.parseInt(filmId)) {
                        control = false;
                    }
                }
            }
        }
        if (!filmCategory.isEmpty() && personCategory.equals("User:") && control && Integer.parseInt(ratingPoint) >= 1 && Integer.parseInt(ratingPoint) <= 10) {
            if (filmCategory.equals("FeatureFilm:")) {
                for (FeatureFilm f : films.featureFilms) {
                    if (f.getId() == Integer.parseInt(filmId)) {
                        f.addRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                        writer.println("Film rated successfully");
                        writer.println("Film type: FeatureFilm");
                        writer.println("Film title: " + f.getTitle() + "\n");
                    }
                }
            } else if (filmCategory.equals("ShortFilm:")) {
                for (ShortFilm s : films.shortFilms) {
                    if (s.getId() == Integer.parseInt(filmId)) {
                        s.addRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                        writer.println("Film rated successfully");
                        writer.println("Film type: ShortFilm");
                        writer.println("Film title: " + s.getTitle() + "\n");
                    }
                }
            } else if (filmCategory.equals("TVSeries:")) {
                for (TVSeries t : films.tvSeries) {
                    if (t.getId() == Integer.parseInt(filmId)) {
                        t.addRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                        writer.println("Film rated successfully");
                        writer.println("Film type: TVSeries");
                        writer.println("Film title: " + t.getTitle() + "\n");
                    }
                }
            } else if (filmCategory.equals("Documentary:")) {
                for (Documentary d : films.documentaries) {
                    if (d.getId() == Integer.parseInt(filmId)) {
                        d.addRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                        writer.println("Film rated successfully");
                        writer.println("Film type: Documentary");
                        writer.println("Film title: " + d.getTitle() + "\n");
                    }
                }
            }

            for (User user : peoples.users) {
                if (user.getId() == Integer.parseInt(userId)) {
                    user.addRate(Integer.parseInt(filmId), Integer.parseInt(ratingPoint));
                }
            }
        } else {
            if (control) {
                writer.println("Command Failed\n" + "User ID: " + userId + "\n" + "Film ID: " + filmId + "\n");
            } else {
                writer.println("This film was earlier rated\n");
            }

        }
        if(Integer.parseInt(ratingPoint) < 1 || Integer.parseInt(ratingPoint) > 10){
            System.out.println("rate should be between 1 and 10.");
        }
        writer.println(
                "-----------------------------------------------------------------------------------------------------");

    }

    public void editRate(Films films, Peoples peoples, String userId, String filmId, String ratingPoint) {
        if (Integer.parseInt(ratingPoint) >= 1 && Integer.parseInt(ratingPoint) <= 10) {
            writer.println("EDIT\t" + "RATE\t" + userId + " " + filmId + " " + ratingPoint + "\n");
            String filmCategory = findFilm(films.allFilmsId, filmId);
            String personCategory = findPerson(peoples.allPeoplesId, userId);
            boolean control = false;
            for (User user : peoples.users) {
                if (user.getId() == Integer.parseInt(userId)) {
                    for (int filmid : user.getRateList()) {
                        if (filmid == Integer.parseInt(filmId)) {
                            control = true;
                        }
                    }
                }
            }
            if (!filmCategory.isEmpty() && !personCategory.isEmpty() && control) {
                if (filmCategory.equals("FeatureFilm:")) {
                    for (FeatureFilm f : films.featureFilms) {
                        if (f.getId() == Integer.parseInt(filmId)) {
                            f.editRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                            writer.println("New ratings done successfully");
                            writer.println("Film title: " + f.getTitle());
                            writer.println("Your rating: " + ratingPoint);

                        }
                    }
                } else if (filmCategory.equals("ShortFilm:")) {
                    for (ShortFilm s : films.shortFilms) {
                        if (s.getId() == Integer.parseInt(filmId)) {
                            s.editRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                            writer.println("New ratings done successfully");
                            writer.println("Film title: " + s.getTitle());
                            writer.println("Your rating: " + ratingPoint);
                        }
                    }
                } else if (filmCategory.equals("TVSeries:")) {
                    for (TVSeries t : films.tvSeries) {
                        if (t.getId() == Integer.parseInt(filmId)) {
                            t.editRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                            writer.println("New ratings done successfully");
                            writer.println("Film title: " + t.getTitle());
                            writer.println("Your rating: " + ratingPoint);
                        }
                    }
                } else if (filmCategory.equals("Documentary:")) {
                    for (Documentary d : films.documentaries) {
                        if (d.getId() == Integer.parseInt(filmId)) {
                            d.editRatingScore(Integer.parseInt(userId), Integer.parseInt(ratingPoint));
                            writer.println("New ratings done successfully");
                            writer.println("Film title: " + d.getTitle());
                            writer.println("Your rating: " + ratingPoint);
                        }
                    }
                }

                for (User user : peoples.users) {
                    if (user.getId() == Integer.parseInt(userId)) {
                        user.editRate(Integer.parseInt(filmId), Integer.parseInt(ratingPoint));
                    }
                }
            } else {
                writer.println("Command Failed\n" + "User ID: " + userId + "\n" + "Film ID: " + filmId);
            }
        } else {
            System.out.println("rate should be between 1 and 10");
        }
        writer.println(
                "\n-----------------------------------------------------------------------------------------------------");

    }

    public void removeRate(Films films, Peoples peoples, String userId, String filmId) {
        writer.println("REMOVE\t" + "RATE\t" + userId + " " + filmId + "\n");
        String filmCategory = findFilm(films.allFilmsId, filmId);
        String personCategory = findPerson(peoples.allPeoplesId, userId);
        boolean control = false;
        for (User user : peoples.users) {
            if (user.getId() == Integer.parseInt(userId)) {
                for (int id : user.getRateList()) {
                    if (id == Integer.parseInt(filmId)) {
                        control = true;
                    }
                }
            }
        }
        if (!filmCategory.isEmpty() && !personCategory.isEmpty() && control) {
            if (filmCategory.equals("FeatureFilm:")) {
                for (FeatureFilm f : films.featureFilms) {
                    if (f.getId() == Integer.parseInt(filmId)) {
                        f.removeRatingScore(Integer.parseInt(userId));
                        writer.println("Your film rating was removed successfully");
                        writer.println("Film title: " + f.getTitle());
                    }
                }
            } else if (filmCategory.equals("ShortFilm:")) {
                for (ShortFilm s : films.shortFilms) {
                    if (s.getId() == Integer.parseInt(filmId)) {
                        s.removeRatingScore(Integer.parseInt(userId));
                        writer.println("Your film rating was removed successfully");
                        writer.println("Film title: " + s.getTitle());
                    }
                }
            } else if (filmCategory.equals("TVSeries:")) {
                for (TVSeries t : films.tvSeries) {
                    if (t.getId() == Integer.parseInt(filmId)) {
                        t.removeRatingScore(Integer.parseInt(userId));
                        writer.println("Your film rating was removed successfully");
                        writer.println("Film title: " + t.getTitle());
                    }
                }
            } else if (filmCategory.equals("Documentary:")) {
                for (Documentary d : films.documentaries) {
                    if (d.getId() == Integer.parseInt(filmId)) {
                        d.removeRatingScore(Integer.parseInt(userId));
                        writer.println("Your film rating was removed successfully");
                        writer.println("Film title: " + d.getTitle());
                    }
                }
            }

            for (User user : peoples.users) {
                if (user.getId() == Integer.parseInt(userId)) {
                    user.removeRate(Integer.parseInt(filmId));
                }
            }
        } else {
            writer.println("Command Failed\n" + "User ID: " + userId + "\n" + "Film ID: " + filmId);
        }
        writer.println(
                "\n-----------------------------------------------------------------------------------------------------");
    }

    public void addFeatureFilm(Films films, Peoples peoples, String s1, String s2, String s3, String s4, String s5,
                               String s6, String s7, String s8, String s9, String s10, String s11) {
        writer.println("ADD FEATUREFILM\t" + s1 + " " + s2 + "\t" + s3 + "\t" + s4 + " " + s5 + "\t" + s6 + " " + s7
                + " " + s8 + "  " + s9 + "\t" + s10 + "\t" + s11 + "\n");
        String filmCategory = findFilm(films.allFilmsId, s1);
        String staffOfFilm = s4 + "," + s7 + "," + s10;
        int[] staffOfFilmList = Arrays.stream(staffOfFilm.split(",")).mapToInt(Integer::parseInt).toArray();
        boolean control = true;
        for (int person : staffOfFilmList) {
            if (findPerson(peoples.allPeoplesId, Integer.toString(person)).isEmpty()) {
                control = false;
                break;
            }
        }
        if (filmCategory.isEmpty() && control) {
            FeatureFilm newFeatureFilm = new FeatureFilm();
            newFeatureFilm.setId(Integer.parseInt(s1));
            newFeatureFilm.setTitle(s2);
            newFeatureFilm.setLanguage(s3);
            newFeatureFilm.setDirectorList(Arrays.stream(s4.split(",")).mapToInt(Integer::parseInt).toArray());
            newFeatureFilm.setRuntime(Integer.parseInt(s5));
            newFeatureFilm.setCountry(s6);
            newFeatureFilm.setPerformers(Arrays.stream(s7.split(",")).mapToInt(Integer::parseInt).toArray());
            newFeatureFilm.setTypes((s8.split(",")));
            newFeatureFilm.setReleaseDate(s9.substring(6));
            newFeatureFilm.setWriters(Arrays.stream(s10.split(",")).mapToInt(Integer::parseInt).toArray());
            newFeatureFilm.setBudget(Long.parseLong(s11));
            films.featureFilms.add(newFeatureFilm);
            films.allFilmsId.add("FeatureFilm:");
            films.allFilmsId.add(s1);
            films.allFilmsIdAndTitle.add(s1);
            films.allFilmsIdAndTitle.add(s2);
            writer.println("FeatureFilm added successfully\n" + "Film ID: " + s1 + "\n" + "Film title: " + s2 + "\n");
        } else {
            writer.println("Command Failed\n" + "Film ID: " + s1 + "\n" + "Film title: " + s2 + "\n");
        }
        writer.println(
                "-----------------------------------------------------------------------------------------------------");

    }

    public void viewFilm(Films films, Peoples peoples, String filmId) {
        writer.println("VIEWFILM " + filmId + "\n");
        String filmCategory = findFilm(films.allFilmsId, filmId);


        if (filmCategory.equals("FeatureFilm:")) {
            for (FeatureFilm f : films.featureFilms) {
                if (f.getId() == Integer.parseInt(filmId)) {
                    writer.println(f.getTitle() + " " + "(" + f.getReleaseDate() + ")");
                    for (String type : f.getTypes()) {
                        writer.print(type);
                        if(!type.equals(f.getTypes()[f.getTypes().length-1])){writer.print(", ");}
                    }

                    writer.print("\nWriters: ");
                    for (int writerId : f.getWriters()) {
                        for (Writer w : peoples.writers) {
                            if (writerId == w.getId()) {
                                writer.print(w.getName() + " " + w.getSurname());
                            }
                        }
                        if(writerId!=f.getWriters()[f.getWriters().length-1]){writer.print(", ");}
                    }
                    writer.print("\nDirectors: ");
                    for (int directorId : f.getDirectorList()) {
                        for (Director director : peoples.directors) {
                            if (directorId == director.getId()) {
                                writer.print(director.getName() + " " + director.getSurname());
                            }
                        }
                        if(directorId!=f.getDirectorList()[f.getDirectorList().length-1]){writer.print(", ");}
                    }
                    writer.print("\nStars: ");
                    for (int performerId : f.getPerformers()) {
                        for (int i = 0; i < peoples.allIdsAndNames.size(); i += 2) {
                            if (performerId == Integer.parseInt(peoples.allIdsAndNames.get(i))) {
                                writer.print(peoples.allIdsAndNames.get(i + 1));
                            }
                        }
                        if(performerId!=f.getPerformers()[f.getPerformers().length-1]){writer.print(", ");}
                    }
                    if (f.getRatingScoreSize() == 0) {
                        writer.println("\nAwaiting for votes");
                    } else {
                        writer.print(
                                "\nRatings: " + f.getRatingScore() + "/10 from " + f.getRatingScoreSize() + " users\n");
                    }
                }
            }
        } else if (filmCategory.equals("ShortFilm:")) {
            for (ShortFilm s : films.shortFilms) {
                if (s.getId() == Integer.parseInt(filmId)) {
                    writer.println(s.getTitle() + " " + "(" + s.getReleaseDate() + ")");
                    for (String type : s.getTypes()) {
                        writer.print(type);
                        if(!type.equals(s.getTypes()[s.getTypes().length-1])){writer.print(", ");}
                    }
                    writer.print("\nWriters: ");
                    for (int writerId : s.getWriters()) {
                        for (Writer w : peoples.writers) {
                            if (writerId == w.getId()) {
                                writer.print(w.getName() + " " + w.getSurname());
                            }
                        }
                        if(writerId!=s.getWriters()[s.getWriters().length-1]){writer.print(", ");}
                    }
                    writer.print("\nDirectors: ");
                    for (int directorId : s.getDirectorList()) {
                        for (Director director : peoples.directors) {
                            if (directorId == director.getId()) {
                                writer.print(director.getName() + " " + director.getSurname());
                            }
                        }
                        if(directorId!=s.getDirectorList()[s.getDirectorList().length-1]){writer.print(", ");}
                    }
                    writer.print("\nStars: ");
                    for (int performerId : s.getPerformers()) {
                        for (int i = 0; i < peoples.allIdsAndNames.size(); i += 2) {
                            if (performerId == Integer.parseInt(peoples.allIdsAndNames.get(i))) {
                                writer.print(peoples.allIdsAndNames.get(i + 1));
                            }
                        }
                        if(performerId!=s.getPerformers()[s.getPerformers().length-1]){writer.print(", ");}
                    }
                    if (s.getRatingScoreSize() == 0) {
                        writer.println("\nAwaiting for votes");
                    } else {
                        writer.print(
                                "\nRatings: " + s.getRatingScore() + "/10 from " + s.getRatingScoreSize() + " users\n");
                    }
                }
            }
        } else if (filmCategory.equals("TVSeries:")) {
            for (TVSeries t : films.tvSeries) {
                if (t.getId() == Integer.parseInt(filmId)) {
                    writer.println(t.getTitle() + " " + "(" + t.getStartDate() + "-" + t.getEndDate() + ")");
                    writer.println(t.getSeasonNumber() + " seasons, " + t.getEpisodeNumber() + " episodes");
                    for (String type : t.getTypes()) {
                        writer.print(type);
                        if(!type.equals(t.getTypes()[t.getTypes().length-1])){writer.print(", ");}
                    }
                    writer.print("\nWriters: ");
                    for (int writerId : t.getWriters()) {
                        for (Writer w : peoples.writers) {
                            if (writerId == w.getId()) {
                                writer.print(w.getName() + " " + w.getSurname());
                            }
                        }
                        if(writerId!=t.getWriters()[t.getWriters().length-1]){writer.print(", ");}
                    }
                    writer.print("\nDirectors: ");
                    for (int directorId : t.getDirectorList()) {
                        for (Director director : peoples.directors) {
                            if (directorId == director.getId()) {
                                writer.print(director.getName() + " " + director.getSurname());
                            }
                        }
                        if(directorId!=t.getDirectorList()[t.getDirectorList().length-1]){writer.print(", ");}
                    }
                    writer.print("\nStars: ");
                    for (int performerId : t.getPerformers()) {
                        for (int i = 0; i < peoples.allIdsAndNames.size(); i += 2) {
                            if (performerId == Integer.parseInt(peoples.allIdsAndNames.get(i))) {
                                writer.print(peoples.allIdsAndNames.get(i + 1));
                            }
                        }
                        if(performerId!=t.getPerformers()[t.getPerformers().length-1]){writer.print(", ");}
                    }

                    if (t.getRatingScoreSize() == 0) {
                        writer.println("\nAwaiting for votes");
                    } else {
                        writer.print(
                                "\nRatings: " + t.getRatingScore() + "/10 from " + t.getRatingScoreSize() + " users\n");
                    }
                }
            }
        } else if (filmCategory.equals("Documentary:")) {
            for (Documentary d : films.documentaries) {
                if (d.getId() == Integer.parseInt(filmId)) {
                    writer.println(d.getTitle() + " " + "(" + d.getReleaseDate() + ")\n");
                    writer.print("Directors: ");
                    for (int directorId : d.getDirectorList()) {
                        for (Director director : peoples.directors) {
                            if (directorId == director.getId()) {
                                writer.print(director.getName() + " " + director.getSurname());
                            }
                        }
                        if(directorId!=d.getDirectorList()[d.getDirectorList().length-1]){writer.print(", ");}
                    }
                    writer.print("\nStars: ");
                    for (int performerId : d.getPerformers()) {
                        for (int i = 0; i < peoples.allIdsAndNames.size(); i += 2) {
                            if (performerId == Integer.parseInt(peoples.allIdsAndNames.get(i))) {
                                writer.print(peoples.allIdsAndNames.get(i + 1));
                            }
                        }
                        if(performerId!=d.getPerformers()[d.getPerformers().length-1]){writer.print(", ");}
                    }
                    if (d.getRatingScoreSize() == 0) {
                        writer.println("\nAwaiting for votes");
                    } else {
                        writer.print(
                                "\nRatings: " + d.getRatingScore() + "/10 from " + d.getRatingScoreSize() + " users\n");
                    }

                }
            }
        } else {
            writer.println("Command Failed\n" + "Film ID: " + filmId + "\n");
        }

        writer.println(
                "\n-----------------------------------------------------------------------------------------------------");

    }

    public void listUserRates(Films films, Peoples peoples, String userId) {
        writer.println("LIST\t" + "USER\t" + userId + " RATES\n");
        boolean control1 = false;
        boolean control2 = false;
        for (User user : peoples.users) {
            if (user.getId() == Integer.parseInt(userId)) {
                control1 = true;
                if (!user.getRateList().isEmpty()) {
                    control2 = true;
                }
            }
        }

        if (control1 && control2) {

            for (User user : peoples.users) {
                if (user.getId() == Integer.parseInt(userId)) {

                    for (int i = 0; i < user.getRateList().size(); i += 2) {
                        for (int j = 0; j < films.allFilmsIdAndTitle.size(); j += 2) {
                            if (user.getRateList().get(i) == Integer.parseInt(films.allFilmsIdAndTitle.get(j))) {
                                writer.println(films.allFilmsIdAndTitle.get(j + 1) + ": "
                                        + user.getRateList().get(i + 1));
                            }
                        }
                    }
                }
            }
        } else if (control1 && !control2) {
            writer.println("There is not any ratings so far\n");
        } else {
            writer.println("Command Failed\n" + "User ID: " + userId);
        }
        writer.println(
                "\n-----------------------------------------------------------------------------------------------------");

    }

    public void listTVSeries(Films films) {
        writer.println("LIST\t" + "FILM\t" + " SERIES\n");
        if (films.tvSeries.size() != 0) {
            for (TVSeries tvSeries : films.tvSeries) {
                writer.println(
                        tvSeries.getTitle() + " " + "(" + tvSeries.getStartDate() + "-" + tvSeries.getEndDate() + ")");
                writer.println(
                        tvSeries.getSeasonNumber() + " seasons and " + tvSeries.getEpisodeNumber() + " episodes\n");

            }
        } else {
            writer.println("No result\n");
        }
        writer.println(
                "-----------------------------------------------------------------------------------------------------");
    }

    public void listFilmsByCountry(Films films, String country) {
        writer.println("LIST\t" + "FILMS\t" + "BY\t" + "COUNTRY\t" + country + "\n");
        boolean control = false;
        for(int i=1;i<films.allFilmsId.size();i+=2){
            if(films.allFilmsId.get(i-1).equals("FeatureFilm:")){
                for (FeatureFilm f : films.featureFilms) {
                    if (f.getCountry().equals(country) && Integer.parseInt(films.allFilmsId.get(i))==f.getId()) {
                        control = true;
                        writer.println("Film title: " + f.getTitle());
                        writer.println(f.getRuntime() + " min");
                        writer.println("Language: " + f.getLanguage() + "\n");
                    }
                }
            }else if(films.allFilmsId.get(i-1).equals("ShortFilm:")){
                for (ShortFilm s : films.shortFilms) {
                    if (s.getCountry().equals(country) && Integer.parseInt(films.allFilmsId.get(i))==s.getId()) {
                        control = true;
                        writer.println("Film title: " + s.getTitle());
                        writer.println(s.getRuntime() + " min");
                        writer.println("Language: " + s.getLanguage() + "\n");
                    }
                }
            }else if(films.allFilmsId.get(i-1).equals("TVSeries:")){
                for (TVSeries t : films.tvSeries) {
                    if (t.getCountry().equals(country) && Integer.parseInt(films.allFilmsId.get(i))==t.getId()) {
                        control = true;
                        writer.println("Film title: " + t.getTitle());
                        writer.println(t.getRuntime() + " min");
                        writer.println("Language: " + t.getLanguage() + "\n");
                    }
                }
            }else if(films.allFilmsId.get(i-1).equals("Documentary:")){
                for (Documentary d : films.documentaries) {
                    if (d.getCountry().equals(country) && Integer.parseInt(films.allFilmsId.get(i))==d.getId()) {
                        control = true;
                        writer.println("Film title: " + d.getTitle());
                        writer.println(d.getRuntime() + " min");
                        writer.println("Language: " + d.getLanguage() + "\n");
                    }
                }
            }

        }if (!control) {
            writer.println("No result\n");
        }
        writer.println(
                "-----------------------------------------------------------------------------------------------------");
    }

    public void listFilmsBeforeYear(Films films, String filmCatagory, String year) {
        writer.println("LIST\t" + filmCatagory + "\t" + "BEFORE\t" + year + "\n");
        boolean control = false;
        if (filmCatagory.equals("FEATUREFILMS")) {
            for (FeatureFilm featureFilm : films.featureFilms) {
                if (Integer.parseInt(featureFilm.getReleaseDate()) < Integer.parseInt(year)) {
                    writer.println("Film title: " + featureFilm.getTitle() + " (" + featureFilm.getReleaseDate() + ")");
                    writer.println(featureFilm.getRuntime() + " min");
                    writer.println("Language: " + featureFilm.getLanguage() + "\n");
                    control = true;
                }
            }
        } else if (filmCatagory.equals("SHORTFILMS")) {
            for (ShortFilm shortFilm : films.shortFilms) {
                if (Integer.parseInt(shortFilm.getReleaseDate()) < Integer.parseInt(year)) {
                    writer.println("Film title: " + shortFilm.getTitle() + " (" + shortFilm.getReleaseDate() + ")");
                    writer.println(shortFilm.getRuntime() + " min");
                    writer.println("Language: " + shortFilm.getLanguage() + "\n");
                    control = true;
                }
            }
        } else if (filmCatagory.equals("TVSERIES")) {//burada release date yok bunu bi sor
            for (TVSeries tvSeries : films.tvSeries) {
                if (Integer.parseInt(tvSeries.getStartDate()) < Integer.parseInt(year)) {
                    writer.println("Film title: " + tvSeries.getTitle() + " (" + tvSeries.getStartDate() + ")");
                    writer.println(tvSeries.getRuntime() + " min");
                    writer.println("Language: " + tvSeries.getLanguage() + "\n");
                    control = true;
                }
            }
        } else if (filmCatagory.equals("DOCUMENTARY")) {
            for (Documentary documentary : films.documentaries) {
                if (Integer.parseInt(documentary.getReleaseDate()) < Integer.parseInt(year)) {
                    writer.println("Film title: " + documentary.getTitle() + " (" + documentary.getReleaseDate() + ")");
                    writer.println(documentary.getRuntime() + " min");
                    writer.println("Language: " + documentary.getLanguage() + "\n");
                    control = true;
                }
            }
        } else {
            writer.println("No result\n");
        }
        if (!control) {
            writer.println("No result\n");
        }
        writer.println(
                "-----------------------------------------------------------------------------------------------------");
    }

    public void listFilmsAfterYear(Films films, String filmCatagory, String year) { //buradaki esitlik kalkacak unutma
        writer.println("LIST\t" + filmCatagory + "\t" + "AFTER\t" + year + "\n");
        boolean control = false;
        if (filmCatagory.equals("FEATUREFILMS")) {
            for (FeatureFilm featureFilm : films.featureFilms) {
                if (Integer.parseInt(featureFilm.getReleaseDate()) >= Integer.parseInt(year)) {
                    writer.println("Film title: " + featureFilm.getTitle() + " (" + featureFilm.getReleaseDate() + ")");
                    writer.println(featureFilm.getRuntime() + " min");
                    writer.println("Language: " + featureFilm.getLanguage() + "\n");
                    control = true;
                }
            }
        } else if (filmCatagory.equals("SHORTFILMS")) {
            for (ShortFilm shortFilm : films.shortFilms) {
                if (Integer.parseInt(shortFilm.getReleaseDate()) >= Integer.parseInt(year)) {
                    writer.println("Film title: " + shortFilm.getTitle() + " (" + shortFilm.getReleaseDate() + ")");
                    writer.println(shortFilm.getRuntime() + " min");
                    writer.println("Language: " + shortFilm.getLanguage() + "\n");
                    control = true;
                }
            }
        } else if (filmCatagory.equals("TVSERIES")) {//burada release date yok bunu bi sor
            for (TVSeries tvSeries : films.tvSeries) {
                if (Integer.parseInt(tvSeries.getStartDate()) >= Integer.parseInt(year)) {
                    writer.println("Film title: " + tvSeries.getTitle() + " (" + tvSeries.getStartDate() + ")");
                    writer.println(tvSeries.getRuntime() + " min");
                    writer.println("Language: " + tvSeries.getLanguage() + "\n");
                    control = true;
                }
            }
        } else if (filmCatagory.equals("DOCUMENTARY")) {
            for (Documentary documentary : films.documentaries) {
                if (Integer.parseInt(documentary.getReleaseDate()) >= Integer.parseInt(year)) {
                    writer.println("Film title: " + documentary.getTitle() + " (" + documentary.getReleaseDate() + ")");
                    writer.println(documentary.getRuntime() + " min");
                    writer.println("Language: " + documentary.getLanguage() + "\n");
                    control = true;
                }
            }
        } else {
            writer.println("No result\n");
        }
        if (!control) {
            writer.println("No result\n");
        }
        writer.println(
                "-----------------------------------------------------------------------------------------------------");
    }

    public void listFilmsRate(Films films) {
        writer.println("LIST\t" + "FILMS\t" + "BY\t" + "RATE\t" + "DEGREE\n");
        Collections.sort(films.featureFilms, Comparator.comparing(FeatureFilm::getRatingScore).reversed());
        Collections.sort(films.shortFilms, Comparator.comparing(ShortFilm::getRatingScore).reversed());
        Collections.sort(films.documentaries, Comparator.comparing(Documentary::getRatingScore).reversed());
        Collections.sort(films.tvSeries, Comparator.comparing(TVSeries::getRatingScore).reversed());
        writer.println("FeatureFilm:");
        boolean control = false;
        for (FeatureFilm f : films.featureFilms) {
            writer.println(f.getTitle() + " (" + f.getReleaseDate() + ") Ratings: " + f.getRatingScore() + "/10 from " + f.getRatingScoreSize() + " users");
            control = true;
        }
        if (!control) {
            writer.println("No result");
        }
        control = false;
        writer.println("\nShortFilm:");
        for (ShortFilm s : films.shortFilms) {

            writer.println(s.getTitle() + " (" + s.getReleaseDate() + ") Ratings: " + s.getRatingScore() + "/10 from " + s.getRatingScoreSize() + " users");
            control = true;

        }
        if (!control) {
            writer.println("No result");
        }
        control = false;
        writer.println("\nDocumentary:");
        for (Documentary d : films.documentaries) {

            writer.println(d.getTitle() + " (" + d.getReleaseDate() + ") Ratings: " + d.getRatingScore() + "/10 from " + d.getRatingScoreSize() + " users");
            control = true;

        }
        if (!control) {
            writer.println("No result");
        }
        control = false;
        writer.println("\nTVSeries:");
        for (TVSeries t : films.tvSeries) {

            writer.println(t.getTitle() + " (" + t.getStartDate() + "-" + t.getEndDate() + ") Ratings: " + t.getRatingScore() + "/10 from " + t.getRatingScoreSize() + " users");
            control = true;

        }
        if (!control) {
            writer.println("No result");
        }
        writer.println(
                "\n-----------------------------------------------------------------------------------------------------");

    }

    public void listArtistByCountry(Peoples peoples, String country) {
        writer.println("LIST\t" + "ARTISTS\t" + "FROM\t" + country + "\n");
        boolean control = false;
        writer.println("Directors:");
        for (Director d : peoples.directors) {
            if (d.getCountry().equals(country)) {
                writer.println(d.getName() + " " + d.getSurname() + " " + d.getAgent());
                control = true;
            }
        }
        if (!control) {
            writer.println("No result");
        }
        control = false;
        writer.println("\nWriters:");
        for (Writer w : peoples.writers) {
            if (w.getCountry().equals(country)) {
                writer.println(w.getName() + " " + w.getSurname() + " " + w.getStyle());
                control = true;
            }
        }
        if (!control) {
            writer.println("No result");
        }
        control = false;
        writer.println("\nActors:");
        for (Actor a : peoples.actors) {
            if (a.getCountry().equals(country)) {
                writer.println(a.getName() + " " + a.getSurname() + " " + a.getHeight() + " cm");
                control = true;
            }
        }
        if (!control) {
            writer.println("No result");
        }
        control = false;
        writer.println("\nChildActors:");
        for (ChildActor c : peoples.childActors) {
            if (c.getCountry().equals(country)) {
                writer.println(c.getName() + " " + c.getSurname() + " " + c.getAge());
                control = true;
            }
        }
        if (!control) {
            writer.println("No result");
        }
        control = false;
        writer.println("\nStuntPerformers:");
        for (StuntPerformer s : peoples.stuntPerformers) {
            if (s.getCountry().equals(country)) {
                writer.println(s.getName() + " " + s.getSurname() + " " + s.getHeight() + " cm");
                control = true;
            }
        }
        if (!control) {
            writer.println("No result");
        }
        writer.println(
                "\n-----------------------------------------------------------------------------------------------------");


    }


}
