package upplic.com.angelavto.presentation.di.modules;


import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import upplic.com.angelavto.domain.executors.AlarmInteractorImpl;
import upplic.com.angelavto.domain.executors.CheckAlarm;
import upplic.com.angelavto.domain.executors.CheckKey;
import upplic.com.angelavto.domain.executors.CreateCar;
import upplic.com.angelavto.domain.executors.DeleteAllCarOptions;
import upplic.com.angelavto.domain.executors.DeleteCar;
import upplic.com.angelavto.domain.executors.GetCarDetail;
import upplic.com.angelavto.domain.executors.GetCars;
import upplic.com.angelavto.domain.executors.GetBeacons;
import upplic.com.angelavto.domain.executors.GetCarsOptions;
import upplic.com.angelavto.domain.executors.GetCarsWithoutSubject;
import upplic.com.angelavto.domain.executors.GetRecordDetail;
import upplic.com.angelavto.domain.executors.GetRecords;
import upplic.com.angelavto.domain.executors.Login;
import upplic.com.angelavto.domain.executors.OffAlarm;
import upplic.com.angelavto.domain.executors.RegisterPushToken;
import upplic.com.angelavto.domain.executors.Registration;
import upplic.com.angelavto.domain.executors.SetStatus;
import upplic.com.angelavto.domain.executors.UpdateCar;
import upplic.com.angelavto.domain.executors.UpdateCarOptions;
import upplic.com.angelavto.domain.interactors.AlarmInteractor;
import upplic.com.angelavto.domain.interactors.Interactor0;
import upplic.com.angelavto.domain.interactors.Interactor1;
import upplic.com.angelavto.domain.models.Alarm;
import upplic.com.angelavto.domain.models.Car;
import upplic.com.angelavto.domain.models.Beacon;
import upplic.com.angelavto.domain.models.CarOptions;
import upplic.com.angelavto.domain.models.DeleteCarResult;
import upplic.com.angelavto.domain.models.LoginDomain;
import upplic.com.angelavto.domain.models.LoginResult;
import upplic.com.angelavto.domain.models.Record;
import upplic.com.angelavto.domain.models.RegistrationDomain;
import upplic.com.angelavto.domain.models.RegistrationResult;
import upplic.com.angelavto.domain.models.Status;
import upplic.com.angelavto.domain.models.UpsertCarResult;
import upplic.com.angelavto.presentation.factories.AppMenuFactory;
import upplic.com.angelavto.presentation.factories.AvtoViewPagerFactory;
import upplic.com.angelavto.presentation.factories.LoginViewPagerFactory;
import upplic.com.angelavto.presentation.utils.FragmentRouter;
import upplic.com.angelavto.presentation.factories.FragmentsFactory;
import upplic.com.angelavto.presentation.views.activities.BaseActivity;

@Module
public class ActivityModule {

    public static final String GET_BEACONS = "getbeacons";
    public static final String GET_CARS = "getcars";
    public static final String GET_CARS_WITHOUT_SUBJECT = "getcarswithoutsubject";
    public static final String GET_CAR_OPTIONS = "getcarsoptions";
    public static final String CREATE_CAR = "createcar";
    public static final String UPDATE_CAR = "updatecar";
    public static final String UPDATE_CAR_OPTIONS = "updatecaroptions";
    public static final String GET_CAR_DETAIL = "getcardetail";
    public static final String DELETE_CAR = "deletecar";
    public static final String REGISTRATION = "registration";
    public static final String LOGIN = "login";
    public static final String DELETE_ALL_CAR_OPTIONS = "deleteallcaroptions";
    public static final String CHECK_KEY = "checkkey";
    public static final String SET_STATUS = "setstatus";
    public static final String GET_RECORDS = "getrecords";
    public static final String GET_RECORD_DETAIL = "getrecorddetail";
    public static final String CHECK_ALARM = "checkalarm";
    public static final String SEND_GCM_TOKEN = "sendgcmtoken";
    public static final String OFF_ALARM = "offalarm";
    public static final String ALARM = "alarminteractor";

    private FragmentRouter.RouterBilder mRouterBilder;
    private FragmentsFactory mFragmentsFactory;
    private AppMenuFactory mAppMenuFactory;
    private AvtoViewPagerFactory.Builder mAvtoViewPagerFactoryBuilder;
    private LoginViewPagerFactory mLoginViewPagerFactory;

    public ActivityModule(BaseActivity activity) {
        mRouterBilder = new FragmentRouter.RouterBilder();
        mFragmentsFactory = new FragmentsFactory();
        mAppMenuFactory = new AppMenuFactory(activity);
        mAvtoViewPagerFactoryBuilder = new AvtoViewPagerFactory.Builder();
        mLoginViewPagerFactory = new LoginViewPagerFactory();
    }

    @Provides
    public FragmentRouter.RouterBilder provideRouterBilder() {
        return mRouterBilder;
    }

    @Provides
    public FragmentsFactory provideFragmentsFactory() {
        return mFragmentsFactory;
    }

    @Provides
    public AppMenuFactory provideAppMenuFactory() {
        return mAppMenuFactory;
    }

    @Provides
    public AvtoViewPagerFactory.Builder provideAvtoViewPagerFactoryBuilder() {
        return mAvtoViewPagerFactoryBuilder;
    }

    @Provides
    public LoginViewPagerFactory provideLoginViewPagerFactory() {
        return mLoginViewPagerFactory;
    }

    @Provides
    @Named(GET_BEACONS)
    public Interactor0<List<Beacon>> provideGetBeacons(GetBeacons getBeacons) {
        return getBeacons;
    }

    @Provides
    @Named(GET_CARS)
    public Interactor0<List<Car>> provideGetCars(GetCars getCars) {
        return getCars;
    }

    @Provides
    @Named(GET_CARS_WITHOUT_SUBJECT)
    public Interactor0<List<Car>> provideGetCarsWihtoutSubject(GetCarsWithoutSubject getCars) {
        return getCars;
    }

    @Provides
    @Named(GET_CAR_OPTIONS)
    public Interactor0<List<CarOptions>> provideGetCarsOptions(GetCarsOptions getCarsOptions) {
        return getCarsOptions;
    }

    @Provides
    @Named(CREATE_CAR)
    public Interactor1<UpsertCarResult, Car> provideCreateCar(CreateCar createCar) {
        return createCar;
    }

    @Provides
    @Named(GET_CAR_DETAIL)
    public Interactor1<Car, Integer> provideGetCarDetail(GetCarDetail getCarDetail) {
        return getCarDetail;
    }

    @Provides
    @Named(UPDATE_CAR)
    public Interactor1<UpsertCarResult, Car> provideUpdateCar(UpdateCar updateCar) {
        return updateCar;
    }

    @Provides
    @Named(UPDATE_CAR_OPTIONS)
    public Interactor1<CarOptions, CarOptions> provideUpdateCarOptions(UpdateCarOptions updateCarOptions) {
        return updateCarOptions;
    }

    @Provides
    @Named(DELETE_CAR)
    public Interactor1<DeleteCarResult, Car> provideDeleteCar(DeleteCar deleteCar) {
        return deleteCar;
    }

    @Provides
    @Named(DELETE_ALL_CAR_OPTIONS)
    public Interactor0<Integer> provideDeleteAllCarOptions(DeleteAllCarOptions deleteAllCarOptions) {
        return deleteAllCarOptions;
    }

    @Provides
    @Named(REGISTRATION)
    public Interactor1<RegistrationResult, RegistrationDomain> provaideRegistration(Registration registration) {
        return registration;
    }

    @Provides
    @Named(LOGIN)
    public Interactor1<LoginResult, LoginDomain> provaideLogin(Login login) {
        return login;
    }

    @Provides
    @Named(CHECK_KEY)
    public Interactor0<Boolean> provideCheckKey(CheckKey checkKey) {
        return checkKey;
    }

    @Provides
    @Named(SET_STATUS)
    public Interactor1<Status, Status> provideSetStatus(SetStatus setStatus) {
        return setStatus;
    }

    @Provides
    @Named(GET_RECORDS)
    public Interactor1<List<Record>, Integer> provideGetRecords(GetRecords getRecords) {
        return getRecords;
    }

    @Provides
    @Named(GET_RECORD_DETAIL)
    public Interactor1<Record, Integer> provideGetRecordDetail(GetRecordDetail getRecordDetail) {
        return getRecordDetail;
    }

    @Provides
    @Named(CHECK_ALARM)
    public Interactor0<List<Alarm>> provideCheckAlarm(CheckAlarm checkAlarm) {
        return checkAlarm;
    }

    @Provides
    @Named(SEND_GCM_TOKEN)
    public Interactor0<String> provideRegisterPushToken(RegisterPushToken registerPushToken) {
        return registerPushToken;
    }

    @Provides
    @Named(OFF_ALARM)
    public Interactor0<String> provideOffAlarm(OffAlarm offAlarm) {
        return offAlarm;
    }

    @Provides
    @Named(ALARM)
    public AlarmInteractor provideAlarmInteractor(AlarmInteractorImpl alarmInteractor) {
        return alarmInteractor;
    }
}
