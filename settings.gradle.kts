pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "I'm pupil"
include(":app")

include(":navigation:admin")
include(":navigation:pupil")
include(":navigation:common")
include(":navigation:core")
include(":navigation:adminServicesPort")
include(":navigation:adminCorePort")
include(":navigation:commonCorePort")

include(":data:common")
include(":data:accounts")
include(":data:admin")
include(":data:pupil")
include(":data:base")

include(":features:admin:home")
include(":features:admin:account")
include(":features:admin:services")
include(":features:admin:admin_details")
include(":features:admin:notifications")
include(":features:admin:post")
include(":features:admin:sign_up")

include(":features:common:password_recovery")
include(":features:common:sign_in")
include(":features:common:news")
include(":features:common:about")
include(":features:common:welcome")
include(":features:common:filters")

include(":services:admin:practice")
include(":services:admin:sections")
include(":services:admin:magazine")
include(":services:admin:students")
include(":services:admin:about")
include(":services:admin:session")
include(":services:admin:statistics")
include(":services:admin:schedule")

include(":services:common:widgets")
include(":services:common:settings")

include(":core:resource")
include(":core:widget")
include(":core:image_crop")
include(":core:common")
include(":core:hilt_core")

include(":domain:admin:services")
include(":domain:admin:port:portServices")

include(":dataPort:admin:portServices")
include(":domain:admin:news")
include(":data:commonEntity")
include(":core:design")
include(":dataPort:common:faculty")
include(":domain:common:faculty")
include(":domain:common:portFaculty")
include(":dataPort:admin:portAuth")
include(":domain:admin:auth")
include(":domain:admin:port:portAuth")
include(":domain:common:news")
include(":domain:common:portNews")
include(":dataPort:common:portNews")
include(":dataPort:admin:portInstitution")
include(":dataPort:common:portInstitutionEvent")
include(":domain:admin:port:portHome")
include(":domain:admin:home")
include(":dataPort:admin:portAccount")
include(":domain:admin:port:portAccount")
include(":domain:admin:account")
include(":dataPort:admin:portAdminDetails")
include(":domain:admin:port:portAdminDetails")
include(":domain:admin:adminDetails")
include(":dataPort:admin:portInstitutionEvent")
include(":domain:admin:port:portPost")
include(":domain:admin:post")
include(":dataPort:admin:portNotification")
include(":domain:admin:port:portNotification")
include(":domain:admin:notification")
include(":dataPort:admin:portConfirmation")
include(":domain:admin:port:portConfirmation")
include(":domain:admin:confirmation")
include(":features:admin:confirmation")
include(":dataPort:common:portAbout")
include(":dataPort:common:portInstitution")
include(":dataPort:admin:portAbout")
include(":domainAdminServices:about")
include(":portDomainServices:admin:portAbout")
include(":dataPort:admin:portStudents")
include(":portDomainServices:admin:portStudents")
include(":domainAdminServices:students")
include(":dataPort:admin:portExam")
include(":portDomainServices:admin:portExam")
include(":domainAdminServices:exam")
include(":dataPort:admin:portSchedule")
include(":portDomainServices:admin:portSchedule")
include(":domainAdminServices:schedule")
include(":dataPort:admin:portMagazine")
include(":portDomainServices:admin:portMagazine")
include(":domainAdminServices:magazine")
include(":dataPort:admin:portStatistics")
include(":portDomainServices:admin:portStatistics")
include(":domainAdminServices:statistics")
include(":dataPort:admin:portSections")
include(":portDomainServices:admin:portSections")
include(":domainAdminServices:sections")
include(":dataPort:admin:portPractice")
include(":portDomainServices:admin:portPractice")
include(":domainAdminServices:practice")
include(":dataPort:common:portServices")
include(":dataPort:pupil:portAuth")
include(":domain:pupil:auth")
include(":domain:pupil:port:portAuth")
include(":features:pupil:sign-up")
include(":navigation:pupilCorePort")
include(":dataPort:pupil:portHome")
include(":dataPort:pupil:portEventDetails")
include(":dataPort:pupil:portAccount")
include(":domain:pupil:port:portHome")
include(":domain:pupil:home")
include(":domain:pupil:port:portEventDetails")
include(":domain:pupil:eventDetails")
include(":domain:pupil:port:portAccount")
include(":domain:pupil:account")
include(":features:pupil:home")
include(":features:pupil:eventDetails")
include(":features:pupil:account")
include(":domain:pupil:port:portServices")
include(":domain:pupil:services")
include(":features:pupil:services")
include(":dataPort:pupil:portPractice")
include(":dataPort:pupil:portAbout")
include(":dataPort:pupil:portSection")
include(":dataPort:pupil:portStudents")
include(":portDomainServices:pupil:portAbout")
include(":domainPupilServices:about")
include(":portDomainServices:pupil:portSection")
include(":domainPupilServices:section")
include(":portDomainServices:pupil:portPractice")
include(":domainPupilServices:practice")
include(":portDomainServices:pupil:portStudents")
include(":domainPupilServices:students")
include(":services:pupil:about")
include(":navigation:pupilServicesPort")
include(":services:pupil:section")
include(":services:pupil:practice")
include(":services:pupil:students")
include(":dataPort:pupil:portSession")
include(":dataPort:pupil:portSchedule")
include(":dataPort:pupil:portStatistics")
include(":dataPort:pupil:portMagazine")
include(":portDomainServices:pupil:portSession")
include(":domainPupilServices:session")
include(":portDomainServices:pupil:portSchedule")
include(":domainPupilServices:schedule")
include(":portDomainServices:pupil:portStatistics")
include(":domainPupilServices:statistics")
include(":portDomainServices:pupil:portMagazine")
include(":domainPupilServices:magazine")
include(":services:pupil:session")
include(":services:pupil:schedule")
include(":services:pupil:statistics")
include(":services:pupil:magazine")
