/*Kurs: 1IK153
        Laboration: Labb1:7
        Kursdeltagare: Michael Beigart
        Termin och datum: 16 nov;*/

package Labb1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Uppgift7 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Arraylist som håller objekt av NordiskGud
        ArrayList <NordiskGud> godList = new ArrayList<>();

        //Strängar med information om olika gudars beskrvning
        String tor = "Till ursprunget och funktionen är Tor besläktad med den fornindiske guden Indra. I stort sett till funktionen, men inte till ursprunget påminner Tor också om den grekiske guden Zeus och den romerske Jupiter.[1] Namnet Tor används ännu idag i sammansättningen \"tordön\" vilket är synonymt med åska. Det är vidare etymologiskt besläktat med lånordet dunder (av lågtyskt dunder) liksom engelskans thunder, tyskans Donner och så vidare.\n" +
                "\n" +
                "I svenskan och i övriga germanska språk har Tor givit namn åt torsdagen. Islänningarna har dock ersatt þórsdagur med fimmtudagur. Motsvarande finns även i finsk mytologi, där fadersgestalten Ukko antagits ha binamnet Perkele och i Baltikum återfinns Perkūnas i Litauen och Pērkons i Lettland. I slaviska språk återfinns Perun med liknande egenskaper som Tor.\n" +
                "\n" +
                "Tacitus rapporterar att germanernas dyrkade Hercules – romarna hade för vana att tolka utländska gudar genom att jämföra dem med liknande romerska – vilket har antagits varit för att båda är hjältar som slåss med odjur, agerar som mänsklighetens försvarare och att båda representeras med ett trubbigt närstridsvapen (Hercules klubba och Tors hammare Mjölner).\n" +
                "\n" +
                "Germanerna tog sedan till sig romarnas sedvänja att bära\" Herculesklubbor\" och bytte ut den mot sin egen motsvarighet. Dock blev hammaren först populär som amulett under vikingatiden när kristendomen blev ett hot mot den gamla religionen.[2]\n" +
                "\n" +
                "Enligt vissa religionshistoriker kan Tor, liksom Indra, från början ha varit en höggud. Vid tiden för våra källor, främst poetiska och prosaiska Eddan, hade dock denna roll övertagits av Oden (Óðinn), och då har Tor omdefinierats som Odens äldste son; Tors mor var jorden (Jörð, möjligen ett sidonamn på Frigg, men detta är oklart). Liknande teorier finns även om Tyr.";

        String balder = "Balder sades vara bäst av asar. Det var omöjligt att säga något ont om honom. Han var vacker, ljus, god, vis och omtyckt, men hans domar ägde inte bestånd. De andra gudarna brukade säga, att så länge de hade kvar Balder, kunde det inte gå alltför illa för dem. Därför blev de mycket rädda, när han började drömma mardrömmar om att han skulle bli mördad. Och trots alla deras ansträngningar blev det också så till slut. Efter Ragnarök skall dock Balder komma tillbaka från dödsriket tillsammans med sin bror Höder för att styra den nya världen.";

        String gunnar = "Gunnar (Gundahar, Gundahari, tyska Gunther, latin Gundaharius eller Gundicharius, fornengelska Gúðere, fornnordiska Gunnarr) var en halvt mytologisk kung av Burgund under tidigt 400-tal. Legender om honom finns nedtecknade i latinska, medeltida medelhögtyska, fornnordiska, och fornengelska texter, som särskilt rör relationer med Sigurd Fafnesbane (Siegfried på tyska) och hans död genom förräderi av hunnern Attila (Atle i fornnordisla sagor).\n" +
                "\n" +
                "Som Gunnar Gjukason (på nysvenska Gjukesson) är han i nordisk mytologi en dödlig människa som svor fostbrödralag med Sigurd. Son till Gjuke och Grimhild.\n" +
                "\n" +
                "Sigurd och Gunnar räddade Brynhild som de både åtrådde, hon, å sin sida, åtrådde Sigurd. Han var dock trolovad med Gunnars syster Gudrun och besvarade därför inte hennes känslor. Av svartsjuka driver Brynhild Gunnar och hans bröder att mörda Sigurd varpå Gunnar blir ägare till Andvares guldskatt. Hunnernas konung Atle gifter sig med Gudrun och lockar Gunnar och dennes bror Högne till sig. De gräver ner sin skatt i floden Rhen innan de tillfångatas och dödas av Atle. Rhenguldet lyckades ingen finna.";

        String loke = "Loke (fornnordiska Loki, svenska dialekter & danska dialekter Locke) är en av asagudarna i nordisk mytologi, trots att han var son till en jätte. I den äldre Poetiska Eddan förekommer Loke i flera av dikterna och är en central figur i några av dem. Namnet Loke har ännu, trots många försök, inte tolkats etymologiskt övertygande.[1] En teori går dock ut på att namnet är kopplat till ett fornnordiskt ord med betydelsen \"stänga\"/\"slutföra\", vilket då skulle kunna hänsyfta på hans roll som den som frambringar undergången och slutet i Ragnarök. Anna Birgitta Rooth menade att Loke från början var en spindel, och hänvisade bland annat till hans dialektala namn \"Locke\" och Lockespindlar[2]. Vissa forskare menar att Loke är identisk med Lodur som också är bror till Oden. Anledningen till detta är att Lodur aldrig nämns i någon annan berättelse än skapelsen av Yggdrasil och människorna. Dock så nämns Loke ofta ihop med Oden och Höner som en trio, som i dikten Reginsmál, berättelsen om Tjatse och den Färöiska folksagan Loka Táttur. I den sistnämnda beskrivs Loke som en positiv (men slug som alltid) figur. Problemet som forskare har med den teorin är att Lokes handlingar längre fram i den Prosaiska Eddan och Loketrätan inte verkar gå ihop med en positiv figur som Lodur. I 1300-talsverket \"Lokrur\" så benämns dock Loke och Lodur som densamme och flera teorier om detta betyder att författarna var bekanta med en likställning från en oral tradition eller dragit slutsatsen från att ha läst Edda dikter finns.[3]";

        String ymer = "Ymer (på fornnordiska Ýmir vilket betyder \"tvilling\"[1] och var ett urtidsväsen i fornnordisk mytologi. Han föddes ur mötet mellan de smältande dropparna från Nifelhem och gnistorna från Muspelhem i Ginnungagap. Samtidigt uppstod en ur-ko, Audhumbla, som livnärde Ymer med sin mjölk.\n" +
                "\n" +
                "Ymer var jättarnas stamfader och ett androgynt väsen, de som enligt Snorre kallas rimtursar. Dessa föddes genom att hans ena ben avlade avkomma med det andra. I hans armhåla föddes Lif och Liftrase.[2]\n" +
                "\n" +
                "Audhumla livnärde sig på att slicka på stenar belagda med salt rimfrost, en sysselsättning som samtidigt skapade gudarnas förfader, Bure. Denne fick en son som hette Bor, som tillsammans med Bestla (en jättedotter) fick tre söner: Oden, Vile och Ve, (alternativt Oden, Höner och Lodur), vilka senare dräpte Ymer och tillverkade världen av hans kropp. Av köttet blev det jord, av blodet vatten, av benen berg, av håret träd, av huvudskålen himlavalvet, och av hjärnan moln. Hans ögon kastades upp på himlen för att bli sol och måne.[3]\n" +
                "\n" +
                "Nästan alla av Ymers avkomlingar drunknade i den dödade Ymers blod, så när som på ett enda par: rimtursen Bergelme och hans husfolk. Från dessa härstammade de jättar som omtalas i myterna. De bosatte sig i Jotunheim uppe i fjällen.\n" +
                "\n" +
                "Ymers namn \"tvilling\" har diskuterats i ett komparativt( jämförande) perspektiv. Det är besläktat med Yama och Yima, indiska och iranska urgudar vars namn betyder \"tvilling\", samt latinets gemini.[4]";

        String garm = "Garm (norröna Garmr) är i Vǫluspá en hund[1] eller varg som står bunden vid Gnipahålan, och vars skällande förebådar Ragnarök. Garm har ibland identifierats med Fenrisulven, och ibland med gudinnan Hels hund, som omtalas i eddadikten Balders drömmar, men som i dikten saknar namn. Snorre Sturlasson är i sin Edda av den uppfattningen att Garm är en hund, men han förknippar den inte med Hel. Vid Ragnarök skall Garm och Tyr, enligt Snorre, strida mot varandra och bli varandras död.";


        String oden = "\n" +
                "Odens ritt in i Valhall på Sleipner, från Tjängvidestenen funnen i Alskogs socken på Gotland.\n" +
                "\n" +
                "Del av hjälm, tunn, pressad bronsplåt från vendeltiden. Funnen i Vendel. Figuren har tolkats som Oden på Sleipner beväpnad med spjutet Gungner samt korparna Hugin och Munin.\n" +
                "Oden eller Odin, på fornnordiska Óðinn, på fornengelska Wōden, fornsaxiska UUôden,[1], på fornhögtyska Uuodan, från urgermanskans rekonstruerade Wōdanaz, är den äldste, störste och visaste av asagudarna i den nordiska mytologin. Han är också krigsgud, skaldekonstens gud och de dödas gud, och är den främste utövaren av sejdkonsten. Namnet Oden har samma etymologiska grund som Od, Frejas försvunne make. Oden hade också flera andra namn, såsom Gangleri (\"Den färdtrötte\"), se vidare lista över namn på Oden. Under namnet Jolnir (\"Julner\") eller Jolfoðr (\"Julfader\") är Oden känd som julens speciella gud. Några namn har tillkommit genom bruket av kenningar i den forntida diktningen, Oden har över 200 namn och smeknamn. Hos nordborna och anglosaxarna ansågs Oden vara runornas skapare. Oden verkar även varit associerad till nekromantik och läkekonst.\n" +
                "\n" +
                "I nordisk mytologi är Oden, Tor och Loke de tre gestalter som uppträder oftast.";


        String brynhild = "Brynhild var en sköldmö i nordisk mytologi. Hon är dotter till sagokungen Budle. Ifrån den tyska medeltidslitteraturens mycket kända verk, Nibelungenlied, är hon främst känd som drottning Brünhilde, Krimhild av Burgunds svägerska.\n" +
                "\n" +
                "Hon har antagits ha historisk bakgrund Brunhilda, drottning av Austrasien.[1]\n" +
                "\n" +
                "I en episod berättas det att hon trotsade Oden genom att välja fel vinnare efter ett slag, varpå Oden lät stänga in henne i ett rum av eld. Sigurd Fafnesbane och Gunnar Gjukesson hade förälskat sig i henne och hon räddas så av Sigurd. Svartsjuka får Brynhild att driva Gunnar och hans bröder till att döda Sigurd (Sigurd var redan gift med Gunnars syster Gudrun Gjukadotter, kallad Krimhild i Nibelungenlied). Brynhild överger sin dödlige make och följer Sigurd genom att sticka ett svärd i sig själv för att sedan förgå med honom på bålet.\n" +
                "\n" +
                "Brynhild fick dottern Aslög tillsammans med Sigurd Fafnesbane. Brynhild hade också en systerson som hette Allsvinn, inte att förblanda med hästen Allsvinn.";


        //Lägger till gudar i ArrayList med namn, släkte, beskrivning
        godList.add(new NordiskGud("Tor", "Ase", tor));
        godList.add(new NordiskGud("Balder", "Ase", balder));
        godList.add(new NordiskGud("Gunnar Gjukesson", "Människa", gunnar));
        godList.add(new NordiskGud("Loke", "Ase", loke));
        godList.add(new NordiskGud("Ymer", "Jätte", ymer));
        godList.add(new NordiskGud("Garm", "Djur", garm));
        godList.add(new NordiskGud("Oden", "Ase", oden));
        godList.add(new NordiskGud("Brynhild", "Valkyria", brynhild));

        //Ny pane som har en listview
        BorderPane pane = new BorderPane();
        ListView <String> theList = new ListView<>();
        theList.setPrefWidth(200);

        //Huvudrubrik samt placering av ListView till vänster
        Label huvudrubrik = new Label("Nordiska gudar och väsen");
        pane.setTop(huvudrubrik);
        huvudrubrik.setFont(Font.font("Times New Roman", FontWeight.BOLD, 35));
        pane.setLeft(theList);

        //Går igenom ArrayList och lägger till alla gudars namn i ListView
        for (NordiskGud i: godList){
            theList.getItems().addAll(i.getNamn());
        }

        //Lyssnare som returnerar det valda indexet
        theList.getSelectionModel().selectedIndexProperty().addListener(ov ->{
            int index = theList.getSelectionModel().getSelectedIndex();
            NordiskGud val = godList.get(index);

            //Namn på gud
            Text text1 = new Text(val.getNamn() + "\n");
            text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));

            //Släkte på gud
            Text text2 = new Text(val.getSlakte() + "\n");
            text2.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 20));

            //Beskrivning av gud
            Text text3 = new Text(val.getBeskrivning());

            //Placerar allt i centerpanen till höger om ListViewen
            TextFlow flow = new TextFlow(text1, text2, text3);

            pane.setCenter(flow);

        });

        //Scene & stage
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Nordiska gudar");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
