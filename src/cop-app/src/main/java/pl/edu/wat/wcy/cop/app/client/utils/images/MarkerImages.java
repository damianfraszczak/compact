/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.utils.images;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.MimeType;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.inject.Singleton;
import pl.edu.wat.wcy.cop.app.shared.AppConstants;


@Singleton
// Defines the contract for marker images.
public interface MarkerImages extends ClientBundle {
    MarkerImages INSTANCE = GWT.create(MarkerImages.class);

    @Source("markers/abseiling.svg")
    @MimeType("image/svg+xml")
    DataResource abseiling();

    @Source("markers/accounting.svg")
    @MimeType("image/svg+xml")
    DataResource accounting();

    @Source("markers/airport.svg")
    @MimeType("image/svg+xml")
    DataResource airport();

    @Source("markers/amusement-park.svg")
    @MimeType("image/svg+xml")
    DataResource amusement_park();

    @Source("markers/aquarium.svg")
    @MimeType("image/svg+xml")
    DataResource aquarium();

    @Source("markers/archery.svg")
    @MimeType("image/svg+xml")
    DataResource archery();

    @Source("markers/art-gallery.svg")
    @MimeType("image/svg+xml")
    DataResource art_gallery();

    @Source("markers/atm.svg")
    @MimeType("image/svg+xml")
    DataResource atm();

    @Source("markers/bakery.svg")
    @MimeType("image/svg+xml")
    DataResource bakery();

    @Source("markers/bank.svg")
    @MimeType("image/svg+xml")
    DataResource bank();

    @Source("markers/bar.svg")
    @MimeType("image/svg+xml")
    DataResource bar();

    @Source("markers/baseball.svg")
    @MimeType("image/svg+xml")
    DataResource baseball();

    @Source("markers/beauty-salon.svg")
    @MimeType("image/svg+xml")
    DataResource beauty_salon();

    @Source("markers/bicycle-store.svg")
    @MimeType("image/svg+xml")
    DataResource bicycle_store();

    @Source("markers/bicycling.svg")
    @MimeType("image/svg+xml")
    DataResource bicycling();

    @Source("markers/boat-ramp.svg")
    @MimeType("image/svg+xml")
    DataResource boat_ramp();

    @Source("markers/boat-tour.svg")
    @MimeType("image/svg+xml")
    DataResource boat_tour();

    @Source("markers/boating.svg")
    @MimeType("image/svg+xml")
    DataResource boating();

    @Source("markers/book-store.svg")
    @MimeType("image/svg+xml")
    DataResource book_store();

    @Source("markers/bowling-alley.svg")
    @MimeType("image/svg+xml")
    DataResource bowling_alley();

    @Source("markers/braille.svg")
    @MimeType("image/svg+xml")
    DataResource braille();

    @Source("markers/bus-station.svg")
    @MimeType("image/svg+xml")
    DataResource bus_station();

    @Source("markers/cafe.svg")
    @MimeType("image/svg+xml")
    DataResource cafe();

    @Source("markers/campground.svg")
    @MimeType("image/svg+xml")
    DataResource campground();

    @Source("markers/canoe.svg")
    @MimeType("image/svg+xml")
    DataResource canoe();

    @Source("markers/car-dealer.svg")
    @MimeType("image/svg+xml")
    DataResource car_dealer();

    @Source("markers/car-rental.svg")
    @MimeType("image/svg+xml")
    DataResource car_rental();

    @Source("markers/car-repair.svg")
    @MimeType("image/svg+xml")
    DataResource car_repair();

    @Source("markers/car-wash.svg")
    @MimeType("image/svg+xml")
    DataResource car_wash();

    @Source("markers/casino.svg")
    @MimeType("image/svg+xml")
    DataResource casino();

    @Source("markers/cemetery.svg")
    @MimeType("image/svg+xml")
    DataResource cemetery();

    @Source("markers/chairlift.svg")
    @MimeType("image/svg+xml")
    DataResource chairlift();

    @Source("markers/church.svg")
    @MimeType("image/svg+xml")
    DataResource church();

    @Source("markers/circle.svg")
    @MimeType("image/svg+xml")
    DataResource circle();

    @Source("markers/city-hall.svg")
    @MimeType("image/svg+xml")
    DataResource city_hall();

    @Source("markers/climbing.svg")
    @MimeType("image/svg+xml")
    DataResource climbing();

    @Source("markers/closed-captioning.svg")
    @MimeType("image/svg+xml")
    DataResource closed_captioning();

    @Source("markers/clothing-store.svg")
    @MimeType("image/svg+xml")
    DataResource clothing_store();

    @Source("markers/compass.svg")
    @MimeType("image/svg+xml")
    DataResource compass();

    @Source("markers/convenience-store.svg")
    @MimeType("image/svg+xml")
    DataResource convenience_store();

    @Source("markers/courthouse.svg")
    @MimeType("image/svg+xml")
    DataResource courthouse();

    @Source("markers/cross-country-skiing.svg")
    @MimeType("image/svg+xml")
    DataResource cross_country_skiing();

    @Source("markers/crosshairs.svg")
    @MimeType("image/svg+xml")
    DataResource crosshairs();

    @Source("markers/dentist.svg")
    @MimeType("image/svg+xml")
    DataResource dentist();

    @Source("markers/department-store.svg")
    @MimeType("image/svg+xml")
    DataResource department_store();

    @Source("markers/diving.svg")
    @MimeType("image/svg+xml")
    DataResource diving();

    @Source("markers/doctor.svg")
    @MimeType("image/svg+xml")
    DataResource doctor();

    @Source("markers/electrician.svg")
    @MimeType("image/svg+xml")
    DataResource electrician();

    @Source("markers/electronics-store.svg")
    @MimeType("image/svg+xml")
    DataResource electronics_store();

    @Source("markers/embassy.svg")
    @MimeType("image/svg+xml")
    DataResource embassy();

    @Source("markers/expand.svg")
    @MimeType("image/svg+xml")
    DataResource expand();

    @Source("markers/female.svg")
    @MimeType("image/svg+xml")
    DataResource female();

    @Source("markers/finance.svg")
    @MimeType("image/svg+xml")
    DataResource finance();

    @Source("markers/fire-station.svg")
    @MimeType("image/svg+xml")
    DataResource fire_station();

    @Source("markers/fish-cleaning.svg")
    @MimeType("image/svg+xml")
    DataResource fish_cleaning();

    @Source("markers/fishing-pier.svg")
    @MimeType("image/svg+xml")
    DataResource fishing_pier();

    @Source("markers/fishing.svg")
    @MimeType("image/svg+xml")
    DataResource fishing();

    @Source("markers/florist.svg")
    @MimeType("image/svg+xml")
    DataResource florist();

    @Source("markers/food.svg")
    @MimeType("image/svg+xml")
    DataResource food();

    @Source("markers/fullscreen.svg")
    @MimeType("image/svg+xml")
    DataResource fullscreen();

    @Source("markers/funeral-home.svg")
    @MimeType("image/svg+xml")
    DataResource funeral_home();

    @Source("markers/furniture-store.svg")
    @MimeType("image/svg+xml")
    DataResource furniture_store();

    @Source("markers/gas-station.svg")
    @MimeType("image/svg+xml")
    DataResource gas_station();

    @Source("markers/general-contractor.svg")
    @MimeType("image/svg+xml")
    DataResource general_contractor();

    @Source("markers/golf.svg")
    @MimeType("image/svg+xml")
    DataResource golf();

    @Source("markers/grocery-or-supermarket.svg")
    @MimeType("image/svg+xml")
    DataResource grocery_or_supermarket();

    @Source("markers/gym.svg")
    @MimeType("image/svg+xml")
    DataResource gym();

    @Source("markers/hair-care.svg")
    @MimeType("image/svg+xml")
    DataResource hair_care();

    @Source("markers/hang-gliding.svg")
    @MimeType("image/svg+xml")
    DataResource hang_gliding();

    @Source("markers/hardware-store.svg")
    @MimeType("image/svg+xml")
    DataResource hardware_store();

    @Source("markers/health.svg")
    @MimeType("image/svg+xml")
    DataResource health();

    @Source("markers/hindu-temple.svg")
    @MimeType("image/svg+xml")
    DataResource hindu_temple();

    @Source("markers/horse-riding.svg")
    @MimeType("image/svg+xml")
    DataResource horse_riding();

    @Source("markers/hospital.svg")
    @MimeType("image/svg+xml")
    DataResource hospital();

    @Source("markers/ice-fishing.svg")
    @MimeType("image/svg+xml")
    DataResource ice_fishing();

    @Source("markers/ice-skating.svg")
    @MimeType("image/svg+xml")
    DataResource ice_skating();

    @Source("markers/inline-skating.svg")
    @MimeType("image/svg+xml")
    DataResource inline_skating();

    @Source("markers/insurance-agency.svg")
    @MimeType("image/svg+xml")
    DataResource insurance_agency();

    @Source("markers/jet-skiing.svg")
    @MimeType("image/svg+xml")
    DataResource jet_skiing();

    @Source("markers/jewelry-store.svg")
    @MimeType("image/svg+xml")
    DataResource jewelry_store();

    @Source("markers/kayaking.svg")
    @MimeType("image/svg+xml")
    DataResource kayaking();

    @Source("markers/laundry.svg")
    @MimeType("image/svg+xml")
    DataResource laundry();

    @Source("markers/lawyer.svg")
    @MimeType("image/svg+xml")
    DataResource lawyer();

    @Source("markers/library.svg")
    @MimeType("image/svg+xml")
    DataResource library();

    @Source("markers/liquor-store.svg")
    @MimeType("image/svg+xml")
    DataResource liquor_store();

    @Source("markers/local-government.svg")
    @MimeType("image/svg+xml")
    DataResource local_government();

    @Source("markers/location-arrow.svg")
    @MimeType("image/svg+xml")
    DataResource location_arrow();

    @Source("markers/locksmith.svg")
    @MimeType("image/svg+xml")
    DataResource locksmith();

    @Source("markers/lodging.svg")
    @MimeType("image/svg+xml")
    DataResource lodging();

    @Source("markers/low-vision-access.svg")
    @MimeType("image/svg+xml")
    DataResource low_vision_access();

    @Source("markers/male.svg")
    @MimeType("image/svg+xml")
    DataResource male();

    @Source("markers/map-pin.svg")
    @MimeType("image/svg+xml")
    DataResource map_pin();

    @Source("markers/marina.svg")
    @MimeType("image/svg+xml")
    DataResource marina();

    @Source("markers/marker.svg")
    @MimeType("image/svg+xml")
    DataResource marker();

    @Source("markers/mosque.svg")
    @MimeType("image/svg+xml")
    DataResource mosque();

    @Source("markers/motobike-trail.svg")
    @MimeType("image/svg+xml")
    DataResource motobike_trail();

    @Source("markers/movie-rental.svg")
    @MimeType("image/svg+xml")
    DataResource movie_rental();

    @Source("markers/movie-theater.svg")
    @MimeType("image/svg+xml")
    DataResource movie_theater();

    @Source("markers/moving-company.svg")
    @MimeType("image/svg+xml")
    DataResource moving_company();

    @Source("markers/museum.svg")
    @MimeType("image/svg+xml")
    DataResource museum();

    @Source("markers/natural-feature.svg")
    @MimeType("image/svg+xml")
    DataResource natural_feature();

    @Source("markers/night-club.svg")
    @MimeType("image/svg+xml")
    DataResource night_club();

    @Source("markers/open-captioning.svg")
    @MimeType("image/svg+xml")
    DataResource open_captioning();

    @Source("markers/painter.svg")
    @MimeType("image/svg+xml")
    DataResource painter();

    @Source("markers/park.svg")
    @MimeType("image/svg+xml")
    DataResource park();

    @Source("markers/parking.svg")
    @MimeType("image/svg+xml")
    DataResource parking();

    @Source("markers/pet-store.svg")
    @MimeType("image/svg+xml")
    DataResource pet_store();

    @Source("markers/pharmacy.svg")
    @MimeType("image/svg+xml")
    DataResource pharmacy();

    @Source("markers/physiotherapist.svg")
    @MimeType("image/svg+xml")
    DataResource physiotherapist();

    @Source("markers/place-of-worship.svg")
    @MimeType("image/svg+xml")
    DataResource place_of_worship();

    @Source("markers/playground.svg")
    @MimeType("image/svg+xml")
    DataResource playground();

    @Source("markers/plumber.svg")
    @MimeType("image/svg+xml")
    DataResource plumber();

    @Source("markers/point-of-interest.svg")
    @MimeType("image/svg+xml")
    DataResource point_of_interest();

    @Source("markers/police.svg")
    @MimeType("image/svg+xml")
    DataResource police();

    @Source("markers/political.svg")
    @MimeType("image/svg+xml")
    DataResource political();

    @Source("markers/post-box.svg")
    @MimeType("image/svg+xml")
    DataResource post_box();

    @Source("markers/post-office.svg")
    @MimeType("image/svg+xml")
    DataResource post_office();

    @Source("markers/postal-code-prefix.svg")
    @MimeType("image/svg+xml")
    DataResource postal_code_prefix();

    @Source("markers/postal-code.svg")
    @MimeType("image/svg+xml")
    DataResource postal_code();

    @Source("markers/rafting.svg")
    @MimeType("image/svg+xml")
    DataResource rafting();

    @Source("markers/real-estate-agency.svg")
    @MimeType("image/svg+xml")
    DataResource real_estate_agency();

    @Source("markers/restaurant.svg")
    @MimeType("image/svg+xml")
    DataResource restaurant();

    @Source("markers/roofing-contractor.svg")
    @MimeType("image/svg+xml")
    DataResource roofing_contractor();

    @Source("markers/route-pin.svg")
    @MimeType("image/svg+xml")
    DataResource route_pin();

    @Source("markers/route.svg")
    @MimeType("image/svg+xml")
    DataResource route();

    @Source("markers/rv-park.svg")
    @MimeType("image/svg+xml")
    DataResource rv_park();

    @Source("markers/sailing.svg")
    @MimeType("image/svg+xml")
    DataResource sailing();

    @Source("markers/school.svg")
    @MimeType("image/svg+xml")
    DataResource school();

    @Source("markers/scuba-diving.svg")
    @MimeType("image/svg+xml")
    DataResource scuba_diving();

    @Source("markers/search.svg")
    @MimeType("image/svg+xml")
    DataResource search();

    @Source("markers/sheild.svg")
    @MimeType("image/svg+xml")
    DataResource sheild();

    @Source("markers/shopping-mall.svg")
    @MimeType("image/svg+xml")
    DataResource shopping_mall();

    @Source("markers/sign-language.svg")
    @MimeType("image/svg+xml")
    DataResource sign_language();

    @Source("markers/skateboarding.svg")
    @MimeType("image/svg+xml")
    DataResource skateboarding();

    @Source("markers/ski-jumping.svg")
    @MimeType("image/svg+xml")
    DataResource ski_jumping();

    @Source("markers/skiing.svg")
    @MimeType("image/svg+xml")
    DataResource skiing();

    @Source("markers/sledding.svg")
    @MimeType("image/svg+xml")
    DataResource sledding();

    @Source("markers/snow-shoeing.svg")
    @MimeType("image/svg+xml")
    DataResource snow_shoeing();

    @Source("markers/snow.svg")
    @MimeType("image/svg+xml")
    DataResource snow();

    @Source("markers/snowboarding.svg")
    @MimeType("image/svg+xml")
    DataResource snowboarding();

    @Source("markers/snowmobile.svg")
    @MimeType("image/svg+xml")
    DataResource snowmobile();

    @Source("markers/spa.svg")
    @MimeType("image/svg+xml")
    DataResource spa();

    @Source("markers/square-pin.svg")
    @MimeType("image/svg+xml")
    DataResource square_pin();

    @Source("markers/square-rounded.svg")
    @MimeType("image/svg+xml")
    DataResource square_rounded();

    @Source("markers/square.svg")
    @MimeType("image/svg+xml")
    DataResource square();

    @Source("markers/stadium.svg")
    @MimeType("image/svg+xml")
    DataResource stadium();

    @Source("markers/storage.svg")
    @MimeType("image/svg+xml")
    DataResource storage();

    @Source("markers/store.svg")
    @MimeType("image/svg+xml")
    DataResource store();

    @Source("markers/subway-station.svg")
    @MimeType("image/svg+xml")
    DataResource subway_station();

    @Source("markers/surfing.svg")
    @MimeType("image/svg+xml")
    DataResource surfing();

    @Source("markers/swimming.svg")
    @MimeType("image/svg+xml")
    DataResource swimming();

    @Source("markers/synagogue.svg")
    @MimeType("image/svg+xml")
    DataResource synagogue();

    @Source("markers/taxi-stand.svg")
    @MimeType("image/svg+xml")
    DataResource taxi_stand();

    @Source("markers/tennis.svg")
    @MimeType("image/svg+xml")
    DataResource tennis();

    @Source("markers/toilet.svg")
    @MimeType("image/svg+xml")
    DataResource toilet();

    @Source("markers/trail-walking.svg")
    @MimeType("image/svg+xml")
    DataResource trail_walking();

    @Source("markers/train-station.svg")
    @MimeType("image/svg+xml")
    DataResource train_station();

    @Source("markers/transit-station.svg")
    @MimeType("image/svg+xml")
    DataResource transit_station();

    @Source("markers/travel-agency.svg")
    @MimeType("image/svg+xml")
    DataResource travel_agency();

    @Source("markers/unisex.svg")
    @MimeType("image/svg+xml")
    DataResource unisex();

    @Source("markers/university.svg")
    @MimeType("image/svg+xml")
    DataResource university();

    @Source("markers/veterinary-care.svg")
    @MimeType("image/svg+xml")
    DataResource veterinary_care();

    @Source("markers/viewing.svg")
    @MimeType("image/svg+xml")
    DataResource viewing();

    @Source("markers/volume-control-telephone.svg")
    @MimeType("image/svg+xml")
    DataResource volume_control_telephone();

    @Source("markers/walking.svg")
    @MimeType("image/svg+xml")
    DataResource walking();

    @Source("markers/waterskiing.svg")
    @MimeType("image/svg+xml")
    DataResource waterskiing();

    @Source("markers/whale-watching.svg")
    @MimeType("image/svg+xml")
    DataResource whale_watching();

    @Source("markers/wheelchair.svg")
    @MimeType("image/svg+xml")
    DataResource wheelchair();

    @Source("markers/wind-surfing.svg")
    @MimeType("image/svg+xml")
    DataResource wind_surfing();

    @Source("markers/zoo.svg")
    @MimeType("image/svg+xml")
    DataResource zoo();

    @Source("markers/zoom-in-alt.svg")
    @MimeType("image/svg+xml")
    DataResource zoom_in_alt();

    @Source("markers/zoom-in.svg")
    @MimeType("image/svg+xml")
    DataResource zoom_in();

    @Source("markers/zoom-out-alt.svg")
    @MimeType("image/svg+xml")
    DataResource zoom_out_alt();

    @Source("markers/zoom-out.svg")
    @MimeType("image/svg+xml")
    DataResource zoom_out();

    @Source("markers/biological_danger.png")
    @ImageOptions(width = AppConstants.DEFAULT_SYMBOL_WIDTH, height = AppConstants.DEFAULT_SYMBOL_HEIGHT)
    ImageResource biological_danger();

    @Source("markers/chemical_danger.png")
    @ImageOptions(width = AppConstants.DEFAULT_SYMBOL_WIDTH, height = AppConstants.DEFAULT_SYMBOL_HEIGHT)
    ImageResource chemical_danger();

    @Source("markers/nuclear_danger.png")
    @ImageOptions(width = AppConstants.DEFAULT_SYMBOL_WIDTH, height = AppConstants.DEFAULT_SYMBOL_HEIGHT)
    ImageResource nuclear_danger();

    @Source("markers/radio_danger.png")
    @ImageOptions(width = AppConstants.DEFAULT_SYMBOL_WIDTH, height = AppConstants.DEFAULT_SYMBOL_HEIGHT)
    ImageResource radiolocation_danger();

    @Source("markers/sapos.png")
    @ImageOptions(width = AppConstants.DEFAULT_SYMBOL_WIDTH, height = AppConstants.DEFAULT_SYMBOL_HEIGHT)
    ImageResource sapos();

    @Source("markers/chemical.png")
    @ImageOptions(width = AppConstants.DEFAULT_SYMBOL_WIDTH, height = AppConstants.DEFAULT_SYMBOL_HEIGHT)
    ImageResource chemical();

    @Source("markers/factory.png")
    @ImageOptions(width = AppConstants.DEFAULT_SYMBOL_WIDTH, height = AppConstants.DEFAULT_SYMBOL_HEIGHT)
    ImageResource factory();
}
