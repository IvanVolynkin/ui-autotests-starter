package ru.vanjo.qa.uiautotestsstarter.common;

import org.testng.annotations.Listeners;
import ru.vanjo.qa.uiautotestsstarter.helpers.database.postgresql.ConnectionSettings;
import ru.vanjo.qa.uiautotestsstarter.helpers.database.postgresql.PostgresqlDatabaseClient;

/**
 * Base class for all automate tests.
 */
@Listeners({TestListener.class})
public class BaseWebTest {

    protected static PostgresqlDatabaseClient databaseClient = new PostgresqlDatabaseClient(new ConnectionSettings(
            Configuration.instance().getConfig().getString("postgresql.user"),
            Configuration.instance().getConfig().getString("postgresql.password"),
            Configuration.instance().getConfig().getString("postgresql.url")
    ));
}
