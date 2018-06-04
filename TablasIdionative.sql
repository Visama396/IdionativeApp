/*
 * Tabla USUARIOS
 * Voy a usar el email como primary key ya que este no se puede repetir
 * El usuario y contraseña van a tener un tope de 25 carácteres
 * Hay 3 tipos de usuarios: estudiante (e), profesor (p) y admin (a)
 * Se pueden escoger dos géneros o no especificarlo: mujer (m), hombre (h), nulo (n)
 *
 */
 
CREATE TABLE usuarios (
    email VARCHAR2(35) NOT NULL,
    usuario VARCHAR2(25) NOT NULL,
    passwd VARCHAR2(25) NOT NULL,
    tipo VARCHAR(1) DEFAULT 'E',
    genero VARCHAR2(1) DEFAULT 'N',
    nativo VARCHAR2(3) NOT NULL,
    CONSTRAINT usuarios_PK PRIMARY KEY (email),
    CONSTRAINT tipo_usuario_PK CHECK (tipo IN ('A', 'P', 'E')),
    CONSTRAINT genero_CK CHECK (genero IN ('M', 'H', 'N'))
);

/*
 * Tabla que contendrá noticias y actualizaciones de la aplicación y sitio web
 * Va a tener un id númerico para llevar un orden y además se usará de primary key, por lo que será autoincremental
 * El título tendrá un máximo de 25 carácteres.
 * El contenido del artículo será de 600 carácteres como mucho.
 * También se guardará la fecha de creación o en caso de modificarse, de la fecha en que se modificó (crear TRIGGER).
 * Guardar email del autor (PK) para asi sacar el nombre de usuario.
 *
 */
 
CREATE TABLE actualizaciones (
    id_act NUMBER(10) NOT NULL,
    titulo_act VARCHAR2(25) NOT NULL,
    contenido_act VARCHAR2(600) NOT NULL,
    fecha_act DATE DEFAULT SYSDATE,
    idioma_act VARCHAR2(3) NOT NULL,
    autor_act VARCHAR(35) NOT NULL,
    CONSTRAINT actualizaciones_PK PRIMARY KEY (id_act),
    CONSTRAINT autor_actuali_FK FOREIGN KEY (autor_act) REFERENCES usuarios (email),
    CONSTRAINT idioma_actuali_FK FOREIGN KEY (idioma_act) REFERENCES idiomas (codigo)
);

/*
 * Table for languages
 * code_lang is using ISO 639-2 for names of languages
 * name stores the full name of this language
 * country stores the name of the country where the language is spoken in
 *
 */
 
CREATE TABLE idiomas (
    codigo VARCHAR2(3) NOT NULL,
    nombre VARCHAR2(25) NOT NULL,
    pais VARCHAR2(20) NOT NULL,
    palabra_referencia NUMBER(10),
    CONSTRAINT palabra_referencia_FK FOREIGN KEY (palabra_referencia) REFERENCES palabras (id_palabra),
    CONSTRAINT idiomas_PK PRIMARY KEY (codigo)
);

/*
 * Table for the languages that each user speaks
 *
 */
 
CREATE TABLE habla_idiomas (
    usuario_hi VARCHAR2(35) NOT NULL ,
    idioma_hi VARCHAR2(3) NOT NULL ,
    CONSTRAINT usuario_habla_FK FOREIGN KEY (usuario_hi) REFERENCES usuarios (email),
    CONSTRAINT idioma_habla_FK FOREIGN KEY (idioma_hi) REFERENCES idiomas (codigo),
    CONSTRAINT habla_idiomas_PK PRIMARY KEY (usuario_hi, idioma_hi)
);

/*
 * Table for the languages that each user learns
 *
 */
 
CREATE TABLE aprende_idiomas (
    usuario_ai VARCHAR2(35) NOT NULL ,
    idioma_ai VARCHAR2(3) NOT NULL ,
    CONSTRAINT usuario_aprende_FK FOREIGN KEY (usuario_ai) REFERENCES usuarios (email),
    CONSTRAINT idioma_aprende_FK FOREIGN KEY (idioma_ai) REFERENCES idiomas (codigo),
    CONSTRAINT aprende_idiomas_PK PRIMARY KEY (usuario_ai, idioma_ai)
);

/*
 * Table for courses
 * content will have the information about the course, and the things that people will learn from it
 * The id is just an autoincremental number, and the primary key is that number + autor + the language it's written in.
 */
 
CREATE TABLE cursos (
    id_curso NUMBER(10) NOT NULL,
    titulo_curso VARCHAR2(25) NOT NULL,
    contenido_curso VARCHAR2(200) NOT NULL,
    autor_curso VARCHAR2(35) NOT NULL,
    idioma_curso VARCHAR2(3) NOT NULL,
    CONSTRAINT autor_curso_FK FOREIGN KEY (autor_curso) REFERENCES usuarios (email),
    CONSTRAINT idioma_curso_FK FOREIGN KEY (idioma_curso) REFERENCES idiomas (codigo),
    CONSTRAINT cursos_PK PRIMARY KEY (id_curso, idioma_curso, autor_curso)
);

/*
 * The table for the lessons
 * Each lesson's primary key has its own key and the course id for example:
 * Course of Japanese has id: 03
 * If it's written in english then it becomes: 0301
 * and then the lesson code_lang: 030101, 030102, 030103 and so on
 *
 */
 
CREATE TABLE lecciones (
    id_lecc NUMBER(20) NOT NULL,
    id_curso_lecc NUMBER(10) NOT NULL,
    titulo_lecc VARCHAR2(25) NOT NULL,
    content_lecc VARCHAR2(4000) NOT NULL,
    idioma_lecc VARCHAR2(3) NOT NULL,
    autor_lecc VARCHAR2(35) NOT NULL,
    CONSTRAINT curso_FK FOREIGN KEY (id_curso_lecc, idioma_lecc, autor_lecc) REFERENCES cursos (id_curso, idioma_curso, autor_curso),
    CONSTRAINT leccion_PK PRIMARY KEY (id_lecc, id_curso_lecc, idioma_lecc, autor_lecc)
);

/*
 * The table for all the words of the dictionary
 * They all are up to 70 characters, and there can't be null words, instead a default text saying: "Not added yet" in each column's language
 * word_type has only some possible types: verb, adjective, adverb, noun, pronoun, article, preposition, particle, na-adjective, i-adjective, regular verb, irregular verb, u-verb, ru-verb, special verb.
 */
 
CREATE TABLE palabras (
    id_palabra NUMBER(10) NOT NULL,
    eng VARCHAR2(70),
    esp VARCHAR2(70),
    jpn VARCHAR2(70),
    kana VARCHAR2(70),
    deu VARCHAR2(70),
    ptr VARCHAR2(70),
    CONSTRAINT palabras_PK PRIMARY KEY (id_palabra)
);
/*
 * Table for meanings and examples of words in each language.
 */
 
CREATE TABLE significado_ejemplos (
    id_sig NUMBER(10) NOT NULL,
    significado VARCHAR2(100) NOT NULL,
    ejemplo VARCHAR2(100) NOT NULL,
    idioma_sig VARCHAR2(3) NOT NULL,
    CONSTRAINT palabra_FK FOREIGN KEY (id_sig) REFERENCES palabras(id_palabra),
    CONSTRAINT significado_PK PRIMARY KEY (id_sig),
    CONSTRAINT idioma_significado_FK FOREIGN KEY (idioma_sig) REFERENCES idiomas (codigo)
);
/*
 * Tipos de palabras:
 * Noun -> N
 * Adjective -> A
 * Adverb -> Adv
 * Verb -> V
 * Pronoun -> Pron
 * Preposition -> Prep
 * Article -> Art
 * Conjunction -> Conj
 * Interjection -> Interj
 * Na-adjective -> NaAdj
 * I-adjective -> IAdj
 * Regular Verb -> RV
 * Irregular Verb -> IV
 * Verb Transitive -> VT
 * Verb Intransitive -> VI
 * Godan Verb -> GV
 * Ichidan Verb -> IcV
 * Special Verb -> SpV
 * Particle -> Part
 * Determiner -> Det
 */

CREATE TABLE tipos_palabras (
    idtipo_palabra NUMBER(10) NOT NULL,
    tipo VARCHAR2(10) NOT NULL,
    CONSTRAINT id_palabra_FK FOREIGN KEY (idtipo_palabra) REFERENCES palabras (id_palabra),
    CONSTRAINT tipo_CK CHECK (tipo IN ('N', 'A', 'Adv' ,'V' ,'Pron', 'Prep', 'Art', 'Conj', 'Interj' , 'NaAdj', 'IAdj', 'RV', 'IV', 'VT', 'VI', 'GV' ,'IcV', 'SpV', 'Part', 'Det'))
);