package FunctionLayer;

/**
 *
 * @author Fen
 */
public class RulesAndConstants {
    //All lengths are in CM.
    public final static int DISTANCE_BETWEEN_POSTS = 100;
    public final static int DISTANCE_BETWEEN_FLATROOF_RAFTERS = 50;
    public final static int DISTANCE_BETWEEN_ANGLEDROOF_RAFTERS = 100;
    public final static int LENGTH_UNDER_GROUND = 90;
    public final static int ROOF_WIDTH_EXTRA = 50;
    public final static int ROOF_LENGTH_EXTRA = 50;
    
    //KG
    public final static int CONCRETE_PER_POST = 1;
    
    //STK
    public final static int MOUNT_PER_POST = 1;
    public final static int MOUNT_PER_RAFTER = 2;
    public final static int SCREWS_PER_MOUNT = 4;
    public final static int SCREWS_PER_WALL = 4;
    public final static int TILES_PER_SQUAREMETER = 12;
    
    
    //Preferred materials from DB for, as their itemNumber in DB
    public final static int PREFERRED_MATERIAL_RAFTERS = 1;
    public final static int PREFERRED_MATERIAL_REM = 1;
    public final static int PREFERRED_MATERIAL_POSTS = 2;
    public final static int PREFERRED_MATERIAL_CONCRETE = 8;
    public final static int PREFERRED_MATERIAL_MOUNT = 4;
    public final static int PREFERRED_MATERIAL_SCREWS = 3;
    
    //Material descriptions
    public final static String CARPORT_CONCRETE_DESCRIPTION = "Cement";
    public final static String CARPORT_MOUNTS_POST_DESCRIPTION = "Beslag stolper/rem";
    public final static String CARPORT_MOUNTS_RAFTERS_DESCRIPTION = "Beslag rem/spær";
    public final static String CARPORT_REM_DESCRIPTION = "Remme";
    public final static String CARPORT_RAFTER_FLATROOF_DESCRIPTON = "Spær";
    public final static String CARPORT_RAFTER_ANGLEDROOF_BOTTOM_DESCRIPTION = "Bund spær";
    public final static String CARPORT_RAFTER_ANGLEDROOF_SIDE_DESCRIPTION = "Side spær";
    public final static String CARPORT_POSTS_DESCRIPTION = "Stolper";
    public final static String CARPORT_ROOF_DESCRIPTION = "Tag";
    
    public final static String SCREWS_DESCRIPTION = "Skruer";
    
    public final static String SHED_WALL_DESCRIPTION = "Skur beklædning";
    public final static String SHED_POST_DESCRIPTION = "Skur stolper";
    
    //Calculator parameters
    public final static int MAXLENGTH = 500;
    public final static int MINLENGTH = 150;
    public final static int MAXWIDTH = 500;
    public final static int MINWIDTH = 150;
    public final static int MINHEIGHT = 100;
    public final static int MAXHEIGHT = 500;
    public final static int MINROOFANGLE = 1;
    public final static int MAXROOFANGLE = 80;
    
}
