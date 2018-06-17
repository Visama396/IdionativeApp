INSERT INTO PALABRAS VALUES(1, 'English', 'Inglés', '英語', 'えいご', 'Englisch', 'Inglês');
INSERT INTO PALABRAS VALUES(2, 'Spanish', 'Español', 'スペイン語', 'すぺいんご', 'Spanisch', 'Espanhol');
INSERT INTO PALABRAS VALUES(3, 'Japanese', 'Japonés', '日本語', 'にほんご', 'Japanisch', 'Japonês');
INSERT INTO PALABRAS VALUES(4, 'German', 'Alemán', 'ドイツ語', 'どいつご', 'Deutsch', 'Alemão');
INSERT INTO PALABRAS VALUES(5, 'Portuguese', 'Portugués', 'ポルトガル語', 'ぽるとがるご', 'Portugiesisch', 'Português');
INSERT INTO PALABRAS VALUES(6, 'Computer', 'Ordenador', 'コンピューター', 'こんぴゅーたー', 'Computer', 'Computadora');
INSERT INTO PALABRAS VALUES(7, 'Language', 'Idioma', '言語', 'げんご', 'Sprachen', 'Linguagem');

INSERT INTO IDIOMAS VALUES('eng', 'English', 'United Kingdom', 1);
INSERT INTO IDIOMAS VALUES('esp', 'Español', 'España', 2);
INSERT INTO IDIOMAS VALUES('jpn', '日本語', '日本', 3);
INSERT INTO IDIOMAS VALUES('deu', 'Deutsch', 'Deutschland', 4);
INSERT INTO IDIOMAS VALUES('ptr', 'Português', 'Portugal', 5);

INSERT INTO USUARIOS VALUES('admin@idionative.es', 'Admin', 'Admin1234', 'A', 'N', 'esp');
INSERT INTO USUARIOS VALUES('visama@gmail.com', 'Victor', 'Chelsea', 'E', 'H', 'esp');

INSERT INTO HABLA_IDIOMAS VALUES('admin@idionative.es', 'esp');
INSERT INTO HABLA_IDIOMAS VALUES('visama@gmail.com', 'eng');
INSERT INTO HABLA_IDIOMAS VALUES('visama@gmail.com', 'esp');

INSERT INTO APRENDE_IDIOMAS VALUES('admin@idionative.es', 'eng');
INSERT INTO APRENDE_IDIOMAS VALUES('visama@gmail.com', 'jpn');

INSERT INTO SIGNIFICADO_EJEMPLO VALUES(1, 'English is a germanic language used in United Kingdom, America and Australia among other countries. It\'s the language most used for business.', 'Tom\'s mother can\'t speak English at all.', 'eng');
INSERT INTO SIGNIFICADO_EJEMPLO VALUES(1, 'El inglés es una lengua germánica usada en países como Reino Unido, América y Oceanía entre otros países. Es la lengua más hablada a nivel empresarial del mundo.', 'El inglés es cada vez más requerido a nivel mundial.', 'esp');
INSERT INTO SIGNIFICADO_EJEMPLO VALUES(1, '英語（えいご）とはイギリスとアメリカの国語です。世界中とビジネスでよく使う言語です。', '英語を少し話せます。', 'jpn');

INSERT INTO SIGNIFICADO_EJEMPLO VALUES(2, 'Spanish is a Romance language named for its origins as the native tongue of the inhabitants of Spain.', 'Spanish grammar is really difficult.', 'eng');
INSERT INTO SIGNIFICADO_EJEMPLO VALUES(2, 'Lengua romance que se habla en España, parte de América, Filipinas, Guinea Ecuatorial y otros países.', 'Él está estudiando historia de la literatura Española.', 'esp');
INSERT INTO SIGNIFICADO_EJEMPLO VALUES(2, 'ロマンス諸語の一。スペインのほか、ブラジルを除く中南米諸国などで話される。', 'スペイン語がすてきな言語でしょう？', 'jpn');

INSERT INTO SIGNIFICADO_EJEMPLO VALUES(3, 'Japanese is the language used in the country of the "Rising Sun", called Japan (Nipon).', 'Japanese looks difficult with those characters.', 'eng');
INSERT INTO SIGNIFICADO_EJEMPLO VALUES(3, 'El japonés es una lengua incluida en la familia de lenguas japónicas, hablado por más de 130 millones de personas es el idioma oficial de Japón.', 'El japonés se compone de más de 10000 símbolos distintos.', 'esp');
INSERT INTO SIGNIFICADO_EJEMPLO VALUES(3, '日本の国語です。万葉仮名で書かれた古代日本語からの文献をもつ。', '日本語版のアニメは最高。', 'jpn');

INSERT INTO CURSOS VALUES(1, 'Japonés básico', 'El curso empieza por ver los sistemas de escritura Hiragana y Katakana, uso de partículas gramáticales, y formas básicas verbales.', 'admin@idionative.es', 'esp', 'jpn');
INSERT INTO CURSOS VALUES(2, 'Inglés elemental', 'Este curso contiene vocabulario, frases y oraciones básicas para el día a día. Además de un nivel elemental de gramática.', 'admin@idionative.es', 'esp', 'eng');
INSERT INTO CURSOS VALUES(1, 'Basic Spanish', 'This course includes basic sentences and words for daily user, regular verbs, and basic vocabulary to make simple sentences.', 'admin@idionative.es', 'eng', 'esp');

INSERT INTO LECCIONES VALUES(1, 1, 'El Silabario Hiragana', '<h1 style="text-align:center">El Silabario Hiragana</h1><p>Uno de las 3 escrituras del idioma Japonés es el "Hiragana" <strong>ひらがな</strong>.</p><p>En cada uno de los dos silabarios hay 76 sílabas, cada uno tiene un uso específico.</p><p>Hiragana es usado en palabras de origen japonés, y Katakana es usado en palabras de origen extranjero.</p><center><h2>Vocales</h2><table><tr><th>Español</th><th>Japonés</th></tr><tr><td>A / a</td><td>あ</td></tr><tr><td>E / e</td><td>え</td></tr><tr><td>I / i</td><td>い</td></tr><tr><td>O / o</td><td>お</td></tr><tr><td>U / u</td><td>う</td></tr></table></center>', 'esp');
INSERT INTO LECCIONES VALUES(2, 1, 'Describir con だ', '<h1 style="text-align:center">Usar だ para describir cosas o estados</h1><p>Al añadir esta partícula al final de un sustantivo o adjetivo...</p>', 'esp');