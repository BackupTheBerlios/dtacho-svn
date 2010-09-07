dataSource {
	pooled = true
	driverClassName = "org.hsqldb.jdbcDriver"

    /*
      username and password for this app using to connect the MySQL database
     */
    username = "opendtacho"
	password = "opendtacho"
}
hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='com.opensymphony.oscache.hibernate.OSCacheProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
			//dbCreate = "create-drop" // one of 'create', 'create-drop','update'
			/* using "update" to use the newest database,
			  but just have conflicts with fixtures plugin, have to change to dbCreate="create-drop"
			  dbCreated mapped direct to hbm2dll.value
			 */
            //TODO change to "update"
            dbCreate = "create-drop"

            /*
              url point to the database
              here we have MySQL, standard port is 3306
              don't forget to copy mysql-connector-java-[version]-bin.jar into lib folder
             */
            url = "jdbc:mysql://localhost:3306/opendtacho_db?autoreconnect=true"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:mem:testDb"
		}
	}
	production {
		dataSource {
			dbCreate = "update"
			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}