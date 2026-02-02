# Module Guide

This repository is organized as a Maven multi-module build under `src/`. Each module owns a distinct chunk of the COMPACT platform:

## cop-app
- **Role:** GWT/Spring Boot web experience that renders the Common Operational Picture and orchestrates user workflows.
- **Highlights:** GWT client packaged under `cop-app/src/main/java/pl/.../client`, Spring MVC adapters in `.../server`, and shared DTOs in `.../shared`. Ships as a WAR (`cop-app.war`).
- **External deps:** GWT 2.8, Sencha GXT 4, RestyGWT, Spring Boot web/security.

## cop-services
- **Role:** REST endpoints, integration jobs, and decision-support orchestration.
- **Highlights:** Spring Boot Data REST stack, Feign clients, Swagger (`springfox`).
- **External deps:** Spring Boot starters, OpenFeign, Swagger UI.

## cop-domain
- **Role:** Persistence layer with JPA entities, repositories, and auditing annotations.
- **Highlights:** Domain objects like `Scenario`, `MapConfiguration`, SAR models, plus Hibernate Envers for history tracking.
- **External deps:** Spring Data JPA, Hibernate Envers, Lombok (provided scope).

## cop-decisionsupport
- **Role:** GIS/AI helpers that feed decision-support overlays and analytics.
- **Highlights:** Terrain math, search-and-rescue calculus, adapters that feed `cop-app` visualizations.
- **External deps:** Custom math libs bundled under `dependencies/`, OpenMap artifacts.

## cop-common
- **Role:** Shared DTOs, enums, and serialization helpers used by every other module.
- **Highlights:** Plain Java objects kept free from framework annotations for safe client/server reuse.

## cop-symbol-service
- **Role:** Manages APP-6/MSWiA symbology and raster/vector exports.
- **Highlights:** Batik-based SVG processing, image atlases stored under `src/main/resources/pl/.../symbols`.
- **External deps:** Apache Batik stack, XMLGraphics.

## deploy
- **Role:** Shipping artifacts for operators who only need to deploy the platform.
- **Contents:** Downloaded release `cop-app.war`, production-ready `application.properties`, plus `dockercompose/` (Compose bundle) and `docker/` (Dockerfile build).

Use this document with `README.md` to understand how features traverse modules before you start coding or debugging.
