package upplic.com.angelavto.presentation.di.scopes;


import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import javax.inject.Scope;

@Scope
@Retention(RUNTIME)
public @interface PerService {
}
