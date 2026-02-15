ENGLISH BELOW

# Zadanie rekrutacyjne - System Kategoryzacji Filmów v 1.0
To jest repozytorium z zadaniem rekrutacyjnym, w którym miałem wykonać `System Kategoryzacji Filmów`


## Opis zadania

Zadanie polegało na utworzeniu bazy danych z możliwością zapisu do niej filmów per użytkownik, jest możliwość edycji pól przez użytkownika w każdym momencie oraz strzał do zewnętrznego API pobierając listę filmów.

Z powodu problemów z połączeniem się z API Digikat.pl skorzystałem z:
https://developer.themoviedb.org/reference/search-movie
(The Movie Database API).

## UWAGA
Do prawidłowego działania api potrzebne jest utworzenie konta na w/w stronie oraz utworzenie zmiennej środowiskowej pod nazwą `TMDB_API_TOKEN` i dodanie w niej tokena z utworzonego konta.

Trzeba również utworzyć tabele w bazie danych (w folderze `bin` są skrypty pozwalające utworzenie bazy danych postgres przez docker), w folderze `src/main/resources` są skrypty sql ze schemą oraz przykładowymi danymi.

Hasła dla użytkowników testowych (`user0`, `user1` i `user2`) mogę podać prywatnie.

## Instrukcja
- Utwórz konto użytkownika rejestrując się na stronie
- W zakładce "Filmy" są wyświetlane dodane filmy przez użytkownika (dla nowych użytkowników tabela jest pusta), dane można aktualizować lub usuwać filmy. Jest dodana możliwość sortowania filmów po każdej z kolumn
- W zakładce "Wyszukaj" można wyszukiwać filmy po nazwach angielskich (API działa po angielsku), aby dodać filmy do bazy należy je zaznaczyć checkboxem oraz kliknąć przycisk, a po dodaniu filmy są dostępne w zakładce "Filmy"

## Zgłaszanie bugów
Jeżeli znajdziesz jakieś bugi i/lub chcesz zgłosić jakieś poprawki/usprawnienia - masz wolną rękę do kontaktu ze mną :)

Z góry dziękuję za wszelkie uwagi!

## Recruitment Task – Movie Categorization System v1.0

This is a repository containing a recruitment task in which I was required to implement a Movie Categorization System.

## Task Description

The task consisted of creating a database with the ability to store movies per user. The user can edit fields at any time, and the system also includes a request to an external API to fetch a list of movies.

Due to problems connecting to the Digikat.pl API, I used the following instead:
https://developer.themoviedb.org/reference/search-movie
(The Movie Database API).

## IMPORTANT

For the API to work correctly, you need to create an account on the website mentioned above and set an environment variable named `TMDB_API_TOKEN`, containing the token generated from your account.

You also need to create the database tables (the `bi`n folder contains scripts that allow creating a PostgreSQL database via Docker).
In the `src/main/resources` folder, you will find SQL scripts with the database schema and sample data.

Passwords for the test users (`user0`, `user1`, and `user2`) can be provided privately.

## Instructions

- Create a user account by registering on the website.
- In the “Movies” tab, movies added by the user are displayed (for new users, the table is empty). The data can be updated or movies can be deleted. There is also an option to sort movies by any column.
- In the “Search” tab, you can search for movies by their English titles (the API works in English). To add movies to the database, select them using the checkbox and click the button. Once added, the movies will be available in the “Movies” tab.

## Bug Reporting

If you find any bugs and/or would like to report improvements or enhancements, feel free to contact me 🙂

Thank you in advance for all feedback!
