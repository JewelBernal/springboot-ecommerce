DELETE FROM product;
-- dev's note: deleting every app run to avoid product duplication and error


-- Guitars products
INSERT INTO product (id, product_type, name, price, description, image_url) 
VALUES 
(1, 'Guitar', 'AZ Ibanez THBB10 Tim Henson Signature', '135,728.56', 'Tim Henson''s first AZ Ibanez signature. The THBB10 features an American basswood body bolted to a one-piece roasted maple neck with a roasted maple fingerboard with 24 stainless steel frets, acrylic & abalone split block position markers and luminescen...', '../img/2019_THBB10_1P_01.webp'),
(2, 'Guitar', 'Ibanez TOD10 Tim Henson Signature', '163,447.00', 'The TOD10 reflects Tim''s highly versatile playing style. The TOD10''s striking aesthetic feature may be the fingerboard inlay design which Tim dubbed the Tree of Death. A twist on the iconic Ibanez Tree of Life...', '../img/tod10.png'),
(3, 'Guitar', 'Ibanez TOD10N Tim Henson Signature', '40,894.12', 'TOD10N featuring the Tree of Death inlays converted into classical nylon guitar that produces versatile sounds that fits Polyphia''s music.', '../img/tod10n.png');

-- Pedals product
INSERT INTO product (id, product_type, name, price, description, image_url) 
VALUES 
(4, 'Pedal', 'Ibanez TS9 Tube Screamer Overdrive Pedal', '5,767.57', 'Distortion/Overdrive guitar effect pedal for rich tones.', '../img/ts9-1.webp'),
(5, 'Pedal', 'Boss RC-505 Mk2 Loop Station Tabletop Looper', '34,612.80', 'Tabletop Loop Station with 5 Stereo Tracks, 2 XLR Mic Inputs with Phantom Power, 2 Stereo Line Input Pairs, Onboard FX, Rhythm Generator, and Looping Controls', '../img/looper-1.webp'),
(6, 'Pedal', 'Boss DC-2W Waza Craft Dimension C Pedal', '11,438.44', 'Dimensional Chorus Effects Pedal, with Stereo I/O and 2 Voicing Modes.', '../img/dc2-1.webp');
