package com.dwtrade.application;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.dwtrade.application");

        noClasses()
            .that()
            .resideInAnyPackage("com.dwtrade.application.service..")
            .or()
            .resideInAnyPackage("com.dwtrade.application.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.dwtrade.application.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
