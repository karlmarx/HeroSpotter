package com.karlmarxindustries.herospotter.service;

import com.karlmarxindustries.herospotter.dto.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

@Service
public class ServiceImpl implements ServiceLayer {
    private final String CURSE_FILE = "list.txt";
    private final List<String> ipsumList = new ArrayList<>(Arrays.asList("Lorem ipsum dolor amet distillery selfies glossier chia franzen, celiac esse sed pour-over non disrupt. Mlkshk narwhal poutine waistcoat, kale chips ethical cliche selvage cronut enim succulents mumblecore leggings. Food truck dreamcatcher farm-to-table mlkshk subway tile non nisi. Crucifix franzen pok pok, palo santo occaecat chambray wayfarers asymmetrical edison bulb yuccie disrupt in et. Migas craft beer kitsch, fingerstache umami food truck artisan consectetur consequat asymmetrical pour-over knausgaard duis.",

            "DIY aliqua microdosing hashtag sunt, squid ramps master cleanse enim. Fashion axe heirloom single-origin coffee coloring book offal ipsum do pork belly duis man bun succulents irure consequat. Mixtape truffaut humblebrag woke sunt. Fashion axe photo booth thundercats echo park subway tile tbh.", 
            "Gochujang snackwave hashtag wolf voluptate man bun eiusmod labore single-origin coffee chicharrones. Intelligentsia mixtape paleo tote bag church-key shaman live-edge edison bulb crucifix. Cillum lumbersexual deserunt, pug snackwave tilde proident air plant vexillologist pabst. Id ut meh in taxidermy. Tempor taxidermy etsy offal, direct trade labore tote bag succulents. Consequat tacos irony, ad keffiyeh four loko literally selfies PBR&B jianbing tilde bitters VHS.", 
  
            "Slow-carb mollit copper mug offal asymmetrical laborum blue bottle bespoke. Tilde ex excepteur ugh. Man bun chicharrones viral literally whatever synth bicycle rights venmo cred. Green juice pabst vinyl sunt paleo. Messenger bag cupidatat et mumblecore. Raclette selvage freegan banh mi. Tacos fashion axe four dollar toast hoodie crucifix mumblecore, consectetur pour-over." ,

            "Put a bird on it vexillologist live-edge sint trust fund, blue bottle proident tumeric anim shabby chic. Aliquip lorem kitsch actually vaporware ea celiac pabst tattooed deserunt. Vape dreamcatcher pabst single-origin coffee VHS. Artisan affogato migas irure enamel pin gastropub excepteur brunch austin marfa lyft cupidatat. Waistcoat beard selvage subway tile roof party chillwave food truck. Letterpress air plant 90's, reprehenderit celiac hexagon heirloom ut swag nisi four loko lyft next level." ,

            "Marfa excepteur voluptate bicycle rights, non YOLO crucifix ramps nisi yr health goth tumblr cupidatat cloud bread. Deserunt post-ironic lo-fi 8-bit, 3 wolf moon narwhal labore kickstarter. Brooklyn ullamco authentic, heirloom in vaporware culpa. 3 wolf moon kale chips banjo, microdosing occupy 90's disrupt fashion axe. Asymmetrical celiac sriracha man braid authentic. Crucifix raclette mustache, leggings aesthetic cred franzen." ,

            "Kogi irure listicle direct trade ad umami, small batch freegan hashtag lumbersexual. Irure normcore wolf tumeric food truck knausgaard. Biodiesel live-edge pork belly photo booth listicle. Lo-fi pop-up cardigan anim, dolore nulla chicharrones mixtape. Commodo cold-pressed in ennui kickstarter do proident. Flexitarian cray sint ramps quis snackwave YOLO trust fund salvia prism thundercats green juice tilde church-key four dollar toast. Dolor 90's anim heirloom YOLO sustainable deep v laboris proident retro shaman." ,

            "Do vexillologist velit flannel tote bag helvetica labore pour-over pok pok anim. Air plant esse knausgaard thundercats coloring book flannel. Subway tile brooklyn literally sriracha. Umami vexillologist id quinoa bespoke. Ethical consequat raclette letterpress, deep v pork belly cliche est. Lyft af synth activated charcoal." ,

            "Organic yr PBR&B banh mi labore deserunt artisan gluten-free proident vape YOLO. Before they sold out VHS pork belly, nulla pariatur shaman glossier exercitation adipisicing sustainable listicle et try-hard commodo. Jianbing wolf pariatur bicycle rights, ea prism beard banh mi disrupt magna sed consectetur adaptogen. Franzen shabby chic tbh seitan hella adipisicing paleo eiusmod, hell of keffiyeh. IPhone keffiyeh offal snackwave, plaid four dollar toast banh mi poke beard." ,

            "Cred next level ex hot chicken mixtape beard intelligentsia. Authentic kombucha ut eiusmod, keffiyeh 8-bit enamel pin food truck. Hashtag pitchfork nulla fanny pack letterpress austin ethical sed excepteur. Meggings offal before they sold out pabst small batch fixie, next level neutra leggings meditation." ,
 
            "Post-ironic copper mug iPhone semiotics ethical, narwhal enim schlitz affogato pork belly photo booth. Helvetica 90's roof party exercitation umami copper mug fingerstache deserunt eiusmod dolore. Salvia swag stumptown plaid literally. Nostrud banh mi officia, dolor proident cred readymade synth fashion axe consectetur four loko hexagon. Helvetica dreamcatcher scenester normcore biodiesel palo santo." ,

            "Incididunt succulents cronut roof party, consequat fashion axe yuccie keffiyeh proident aliqua ut leggings kinfolk ad. Chambray culpa semiotics austin roof party. Duis roof party hella ut. Celiac yr vegan, pinterest nulla ut ipsum anim jianbing put a bird on it VHS non synth ethical retro." ,
 
            "Minim you probably haven't heard of them eiusmod, swag mumblecore iceland non pok pok do keytar banjo flexitarian lumbersexual XOXO. Veniam pop-up tacos id ut jean shorts enamel pin. Kogi cliche bicycle rights whatever, kombucha irure pickled fam normcore shabby chic neutra live-edge iPhone lo-fi. Gastropub portland quis vape tattooed. Biodiesel edison bulb ramps taiyaki eiusmod. Twee pork belly truffaut cronut fanny pack." ,

            "Sustainable tote bag cillum single-origin coffee pabst fam, aesthetic affogato pour-over synth mlkshk farm-to-table. Next level keffiyeh migas, kombucha VHS pitchfork organic et duis tilde try-hard anim semiotics YOLO artisan. Kale chips literally meggings adaptogen jean shorts officia wolf roof party you probably haven't heard of them blog nisi copper mug. Vexillologist dolor mixtape eu cred, exercitation actually cold-pressed pabst forage everyday carry subway tile single-origin coffee esse. Gentrify microdosing actually squid copper mug williamsburg tbh. Selvage ut direct trade marfa occaecat, hell of bitters plaid whatever." ,

            "Beard taiyaki health goth roof party, selvage butcher authentic bicycle rights before they sold out dolor austin marfa put a bird on it microdosing. Umami waistcoat seitan hot chicken, distillery you probably haven't heard of them authentic copper mug art party ex nulla salvia cloud bread ennui. Mustache skateboard sartorial tumblr brunch id sint excepteur DIY pabst waistcoat poutine cardigan. Plaid mlkshk humblebrag live-edge, reprehenderit raclette aute kinfolk. Everyday carry dolore try-hard vexillologist eu hella voluptate vape fashion axe. YOLO consequat dolor in kombucha, non pabst." ,

            "Sartorial activated charcoal microdosing, post-ironic umami tattooed adipisicing. Vegan humblebrag iceland exercitation voluptate lomo labore craft beer poke deep v coloring book trust fund ullamco. Commodo plaid cronut, pork belly chillwave kitsch yr bicycle rights lo-fi lumbersexual eiusmod celiac. XOXO man braid voluptate, messenger bag artisan enim microdosing affogato pour-over. Fanny pack selfies taiyaki fashion axe id officia yuccie af blog. Next level 90's church-key ethical, wayfarers yuccie tbh whatever ramps typewriter. Whatever mlkshk banh mi fingerstache, intelligentsia mixtape af jianbing proident truffaut deserunt aliquip mollit kale chips." ,

            "Fingerstache small batch health goth, YOLO in edison bulb quis kombucha single-origin coffee polaroid PBR&B kale chips fugiat. Irure before they sold out meggings gastropub godard crucifix ut qui pariatur narwhal fugiat beard. Cray nulla biodiesel green juice, kogi adaptogen fingerstache. Cliche neutra adipisicing unicorn velit skateboard. Try-hard aute roof party, copper mug fixie franzen excepteur enamel pin seitan forage mumblecore ut. Tousled aesthetic stumptown, humblebrag helvetica vice kinfolk. Hexagon quis four loko, prism put a bird on it ugh fingerstache chartreuse letterpress gochujang synth forage ea franzen af." ,

            "Live-edge yr crucifix eu 90's organic ipsum heirloom everyday carry intelligentsia bespoke. Cray proident whatever aesthetic microdosing literally locavore. Ex velit craft beer iceland duis offal ramps commodo pabst keytar pour-over next level williamsburg. Bitters tattooed trust fund, kombucha dreamcatcher vaporware disrupt raclette blue bottle cloud bread shabby chic. Migas occupy put a bird on it air plant. Aesthetic bicycle rights intelligentsia bitters, post-ironic lo-fi anim dreamcatcher cloud bread chicharrones pour-over deep v YOLO." ,

            "Qui 3 wolf moon deserunt yr woke beard meh. Messenger bag swag polaroid et, air plant taiyaki iPhone cred migas ex godard. Bicycle rights chambray butcher, blog poutine vice irure. Small batch deserunt neutra chambray, lorem unicorn chicharrones. Whatever adaptogen kogi chicharrones. Retro hot chicken ethical, consequat irony try-hard taxidermy kombucha pork belly bicycle rights occaecat deserunt." ,
     
            "Gentrify asymmetrical VHS, butcher tempor put a bird on it 3 wolf moon ea affogato lo-fi culpa bushwick pug skateboard wolf. Pinterest helvetica portland, austin migas chillwave sustainable occupy scenester vegan lomo artisan before they sold out 90's mustache. Duis ut banjo, keytar typewriter et sustainable flexitarian occupy slow-carb lo-fi distillery literally celiac. Edison bulb deserunt plaid messenger bag, drinking vinegar meh chartreuse shoreditch fam hot chicken ut blog typewriter adaptogen."));
//    @Override
//    public boolean isStringProfane(String string) {
//        PythonInterpreter pi = new PythonInterpreter();
//        pi.set("filepath", new PyString("list.txt"));
//        pi.set("testString", new PyString(string));
//        pi.set("count", new PyInteger(0));
//        pi.set("curses", new PyList());
//        pi.exec("with open(filepath) as fp:");
//        pi.exec("    for line in fp.readlines():\n");
//    }
    @Override
    public boolean isStringProfane(String string) {
        List<String> curseList = getCurseList();
        for (String curse : curseList) {
            if (string.toUpperCase().contains(curse.toUpperCase())) {
                return true;
            } else{
                return false;
            }
        }
        return false;
    }
    public String censorString (String string) {
        List<String> curseList = getCurseList();
        for (String curse : curseList) {
            string = string.replaceAll("(?i)" + curse, "[expletive]");
        }
        return string;
    }
    @Override
    public String generateFillerText() {
        Random random = new Random();
        int randomIndex = random.nextInt(ipsumList.size());
        return ipsumList.get(randomIndex);
    }
    public Organization censorAndFillOrg(Organization org) {
        Random random = new Random();
        if (org.getName().equals("")) {
            org.setName("[unnamed organization no. " + random.nextInt(1000000) + "]");
        } else {
            org.setName(censorString(org.getName()));
        }
        if (org.getDescription().equals("")) {
            org.setDescription(generateFillerText());
        } else {
            org.setDescription(censorString(org.getDescription()));
        }
        if (org.getPhoneNumber().equals("")) {
            org.setPhoneNumber("+1-212-555-1212");
        }
        if (org.getAddress().equals("")) {
            org.setAddress("none listed");
            org.setLatitude(32.090505d);
            org.setLongitude(34.765808d);
        }
        if (org.getEmail().equals("")) {
            org.setEmail("unknown@none.org");
        } else {
            org.setEmail(censorString(org.getEmail()));
        }
        if (org.getUrl().equals("")) {
            org.setUrl("none listed");
        } else {
            org.setUrl(censorString(org.getUrl()));
        }
        return org;
    }
    public Location censorAndFillLocation(Location location) {
        Random random = new Random();
        if (location.getName().equals("")) {
            location.setName("[unnamed location no. " + random.nextInt(1000000) + "]");
        } else {
            location.setName(censorString(location.getName()));
        }
        return location;
    }
    private List<String> getCurseList(){
        List<String> curseList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader(CURSE_FILE)));
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                if (!(currentLine.equals("")) && !(currentLine.equals(" ")) ){
                    curseList.add(currentLine);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        return curseList;
    }
    public Power censorAndFillPower(Power power) {
        Random random = new Random();
        if (power.getName().equals("")) {
            power.setName("[unnamed power no. " + random.nextInt(1000000) + "]");
        } else {
            power.setName(censorString(power.getName()));
        }
        if (power.getDescription().equals("")) {
            power.setDescription(generateFillerText());
        } else {
            power.setDescription(censorString(power.getDescription()));
        }
        return power;
    }
    public Super censorAndFillSuper(Super super_) {
        Random random = new Random();
        if (super_.getName().equals("")) {
            super_.setName("[unnamed superhuman no. " + random.nextInt(1000000) + "]");
        } else {
            super_.setName(censorString(super_.getName()));
        }
        if (super_.getDescription().equals("")) {
            super_.setDescription(generateFillerText());
        } else {
            super_.setDescription(censorString(super_.getDescription()));
        }
        return super_;
    }
    public Sighting censorAndFillSighting(Sighting sighting) {
        Random random = new Random();
        if (sighting.getReporterName().equals("")) {
            sighting.setReporterName("[anonymous reporter #" + random.nextInt(1000000) + "]");
        } else {
            sighting.setReporterName(censorString(sighting.getReporterName()));
        }
        if (sighting.getDate() == null) {
            sighting.setDate(LocalDate.now());
        }
        return sighting;
    }
}
