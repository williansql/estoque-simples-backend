BEGIN;


ALTER TABLE IF EXISTS public.items DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.subcategory DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.flow DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.flow DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.flow DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.flow DROP CONSTRAINT IF EXISTS None;



DROP TABLE IF EXISTS public.items;

DROP TABLE IF EXISTS public.person;

CREATE TABLE IF NOT EXISTS public.person
(
    id uuid NOT NULL,
    name character varying NOT NULL,
    surname character varying NOT NULL,
    cpf character varying,
    phone character varying NOT NULL
);

--DROP TABLE IF EXISTS public.category;

CREATE TABLE IF NOT EXISTS public.category
(
    id uuid NOT NULL,
    name character varying NOT NULL,
    PRIMARY KEY (id)
);

--DROP TABLE IF EXISTS public.subcategory;

CREATE TABLE IF NOT EXISTS public.subcategory
(
    id uuid NOT NULL,
    name character varying NOT NULL,
    category_id uuid NOT NULL,
    PRIMARY KEY (id)
    category_id UUID references public.category
);

DROP TABLE IF EXISTS public.supplier;

CREATE TABLE IF NOT EXISTS public.supplier
(
    id uuid NOT NULL,
    name_manufacturer character varying NOT NULL,
    cnpj character varying NOT NULL,
    responsible_name character varying NOT NULL,
    responsible_cpf character varying NOT NULL,
    responsible_phone character varying,
    business_phone character varying NOT NULL,
    responsible_email character varying,
    business_email character varying,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public."user";

CREATE TABLE IF NOT EXISTS public."user"
(
    id uuid NOT NULL,
    username character varying NOT NULL,
    password character varying NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.flow;

CREATE TABLE IF NOT EXISTS public.flow
(
    id uuid NOT NULL,
    person_id uuid,
    items_id uuid,
    supplier_id uuid,
    user_id uuid
);

ALTER TABLE IF EXISTS public.items
    ADD FOREIGN KEY (subcategory_id)
    REFERENCES public.subcategory (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.subcategory
    ADD FOREIGN KEY (id)
    REFERENCES public.category (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.flow
    ADD FOREIGN KEY (person_id)
    REFERENCES public.person (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.flow
    ADD FOREIGN KEY (items_id)
    REFERENCES public.items (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.flow
    ADD FOREIGN KEY (supplier_id)
    REFERENCES public.supplier (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.flow
    ADD FOREIGN KEY (user_id)
    REFERENCES public."user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;