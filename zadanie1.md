# Sprawozdanie Piotr Czajka
## Kod aplikacji->zad.1
LocationDataController wystawia endpoint, dzięki któremu użytkownik może dowiedzieć się o swoim ip oraz aktualnej godzinie
<br> w swojej strefie czasowej. W przypadku budownia aplikacji bez użycia dockera będzie to http://localhost:8080/hello
<br> do sprawdzenia ip wykorzystuje http://checkip.dyndns.org/
<br> do sprawdzenia strefy czasowej na podstawie ip wykorzystuje zewnętrzne api i z użyciem GSONa pobieram potrzebne mi dane
<br> ![Kod aplikacji odpowiedzialny za realizację punktu 1a](1.png)
<br> Na powyższym obrazku zostają pobrane dane o godzinie startu serwera, imieniu oraz nazwisku autora i zostaje to odłożone do logów
<br> ![Kod aplikacji odpowiedzialny za realizację punktu 1a-b](2.png)
<br> na powyższym obrazku zostają pobrane informacje o ip, porcie na którym nasłuchuje serwer, strefie czasowej oraz godzinie w danej strefie
## Zadanie 2
Kod pliku dockerfile, został ustawiony inny timezone dla obrazu niż tz użytkownikaS
<br> ![Kod aplikacji odpowiedzialny za realizację punktu 1a-b](3.png)

## Zadanie 3
a) docker build -f Dockerfile -t sprawko:v2 .
<br> -f pozwala określić nazwę pliku Dockerfile (domyślnie Dockerfile)
<br> -t nadanie tagu obrazowi (wersjonowanie obrazu)
<br> . oznacza, że wszystkie pliki i folderu z tego katalogu będą dostępne do budowy obrazu
<br> b) aby uruchomić zbudowany kontener najpierw sprawdzę jego id za pomocą docker images
![wynik działania docker images](4.png)
<br> na podstawie tego uruchamiam obraz używając polecenia: docker run -p 8000:8080 f0 
<br> -p mapuje port hosta na port kontenera
<br> f0 to początek image_id (jeśli jest wystarczająco unikalna to docker to rozpozna, nie trzeba pisać całego image_id)
<br> c) w konsoli są odkładane logi z pkt 1a) <br>
![info z konsoli](5.png)
<br> informacje o warstwach są wyświetlane za pomocą docker history nazwa_obrazu
![docker history](6.png)