# Zadanie rekrutacyjne - System Kategoryzacji Filmów v 1.0
To jest repozytorium z zadaniem rekrutacyjnym, w którym miałem wykonać `System Kategoryzacji Filmów`


## Opis zadania

Zadanie polegało na utworzeniu bazy danych z możliwością zapisu do niej filmów per użytkownik, jest możliwość edycji pól przez użytkownika w każdym momencie oraz strzał do zewnętrznego API pobierając listę filmów.

Z powodu problemów z połączeniem się z api Digikat.pl skorzystałem z https://developer.themoviedb.org/reference/search-movie

## UWAGA
Do prawidłowego działania api potrzebne jest utworzenie konta na w/w stronie oraz utworzenie zmiennej środowiskowej pod nazwą TMDB_API_TOKEN i dodanie w niej tokena z utworzonego konta.

Trzeba również utworzyć tabele w bazie danych (w folderze bin są skrypty pozwalające utworzenie bazy danych postgres przez docker), w folderze src/main/resources są skrypty sql ze schemą oraz przykładowymi danymi.

Hasła dla użytkowników testowych (user0, user1 i user2) mogę podać prywatnie.

## Instrukcja
- Utwórz konto użytkownika rejestrując się na stronie
- W zakładce "Filmy" są wyświetlane dodane filmy przez użytkownika (dla nowych użytkowników tabela jest pusta), dane można aktualizować lub usuwać filmy. Jest dodana możliwość sortowania filmów po każdej z kolumn
- W zakładce "Wyszukaj" można wyszukiwać filmy po nazwach angielskich (API działa po angielsku), aby dodać filmy do bazy należy je zaznaczyć checkboxem oraz kliknąć przycisk, a po dodaniu filmy są dostępne w zakładce "Filmy"
