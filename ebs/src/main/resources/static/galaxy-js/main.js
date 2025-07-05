const orbitsContainer = document.getElementById('orbits');
  const solarWrapper = document.getElementById('solar-wrapper');

  // Sound Management
  class SoundManager {
    constructor() {
      this.audioContext = null;
      this.sounds = {};
      this.isEnabled = true;
      this.ambientSound = null;
      this.masterVolume = 0.3;
      this.init();
    }

    async init() {
      try {
        // Create audio context on first user interaction
        this.audioContext = new (window.AudioContext || window.webkitAudioContext)();
        
        // Create ambient space sound
        this.createAmbientSound();
        
        // Create planet sounds
        this.createPlanetSounds();
        
        // Create UI sounds
        this.createUISounds();
        
        console.log('Sound system initialized');
      } catch (error) {
        console.log('Audio not supported:', error);
      }
    }

    createAmbientSound() {
      if (!this.audioContext) return;
      
      // Create ambient space drone
      const oscillator1 = this.audioContext.createOscillator();
      const oscillator2 = this.audioContext.createOscillator();
      const gainNode = this.audioContext.createGain();
      
      oscillator1.frequency.setValueAtTime(40, this.audioContext.currentTime);
      oscillator2.frequency.setValueAtTime(60, this.audioContext.currentTime);
      
      oscillator1.type = 'sine';
      oscillator2.type = 'triangle';
      
      gainNode.gain.setValueAtTime(0.1, this.audioContext.currentTime);
      
      oscillator1.connect(gainNode);
      oscillator2.connect(gainNode);
      gainNode.connect(this.audioContext.destination);
      
      oscillator1.start();
      oscillator2.start();
      
      this.ambientSound = { oscillator1, oscillator2, gainNode };
    }

    createPlanetSounds() {
      // Each planet gets a unique frequency based on its orbital characteristics
      const planetFrequencies = {
        'Mercury': 261.63, // C4
        'Venus': 293.66,   // D4
        'Earth': 329.63,   // E4
        'Mars': 349.23,    // F4
        'Jupiter': 392.00, // G4
        'Saturn': 440.00,  // A4
        'Uranus': 493.88,  // B4
        'Neptune': 523.25, // C5
        'Pluto': 554.37,   // C#5
        'Ceres': 369.99,   // F#4
        'Eris': 587.33     // D5
      };

      Object.entries(planetFrequencies).forEach(([planet, freq]) => {
        this.sounds[planet] = { frequency: freq, isPlaying: false };
      });
    }

    createUISounds() {
      this.sounds.click = { frequency: 800, duration: 0.1 };
      this.sounds.hover = { frequency: 1200, duration: 0.05 };
      this.sounds.dance = { frequency: 440, duration: 0.2 };
    }

    playPlanetSound(planetName) {
      if (!this.audioContext || !this.isEnabled) return;
      
      const sound = this.sounds[planetName];
      if (!sound) return;

      // Resume audio context if suspended
      if (this.audioContext.state === 'suspended') {
        this.audioContext.resume();
      }

      const oscillator = this.audioContext.createOscillator();
      const gainNode = this.audioContext.createGain();
      
      oscillator.frequency.setValueAtTime(sound.frequency, this.audioContext.currentTime);
      oscillator.type = 'sine';
      
      gainNode.gain.setValueAtTime(0, this.audioContext.currentTime);
      gainNode.gain.linearRampToValueAtTime(this.masterVolume * 0.3, this.audioContext.currentTime + 0.1);
      gainNode.gain.exponentialRampToValueAtTime(0.01, this.audioContext.currentTime + 1);
      
      oscillator.connect(gainNode);
      gainNode.connect(this.audioContext.destination);
      
      oscillator.start();
      oscillator.stop(this.audioContext.currentTime + 1);
    }

    playUISound(soundName) {
      if (!this.audioContext || !this.isEnabled) return;
      
      const sound = this.sounds[soundName];
      if (!sound) return;

      if (this.audioContext.state === 'suspended') {
        this.audioContext.resume();
      }

      const oscillator = this.audioContext.createOscillator();
      const gainNode = this.audioContext.createGain();
      
      oscillator.frequency.setValueAtTime(sound.frequency, this.audioContext.currentTime);
      oscillator.type = 'triangle';
      
      gainNode.gain.setValueAtTime(0, this.audioContext.currentTime);
      gainNode.gain.linearRampToValueAtTime(this.masterVolume * 0.2, this.audioContext.currentTime + 0.01);
      gainNode.gain.exponentialRampToValueAtTime(0.01, this.audioContext.currentTime + sound.duration);
      
      oscillator.connect(gainNode);
      gainNode.connect(this.audioContext.destination);
      
      oscillator.start();
      oscillator.stop(this.audioContext.currentTime + sound.duration);
    }

    toggle() {
      this.isEnabled = !this.isEnabled;
      
      if (this.ambientSound) {
        const targetVolume = this.isEnabled ? 0.1 : 0;
        this.ambientSound.gainNode.gain.linearRampToValueAtTime(
          targetVolume, 
          this.audioContext.currentTime + 0.5
        );
      }
      
      return this.isEnabled;
    }
  }

  // Initialize sound manager
  const soundManager = new SoundManager();

  // All 11 planets data (including dwarf planets)
  const planets = [
    { name: 'Mercury', orbitRadius: 60, size: 8, color: '#8C7853', speed: 0.020, angle: 0 },
    { name: 'Venus', orbitRadius: 80, size: 12, color: '#FFC649', speed: 0.015, angle: Math.PI / 6 },
    { name: 'Earth', orbitRadius: 100, size: 14, color: '#6B93D6', speed: 0.012, angle: Math.PI / 3 },
    { name: 'Mars', orbitRadius: 130, size: 10, color: '#CD5C5C', speed: 0.010, angle: Math.PI / 2 },
    { name: 'Jupiter', orbitRadius: 170, size: 28, color: '#D8CA9D', speed: 0.008, angle: Math.PI * 2/3 },
    { name: 'Saturn', orbitRadius: 210, size: 24, color: '#FAD5A5', speed: 0.006, angle: Math.PI * 5/6 },
    { name: 'Uranus', orbitRadius: 250, size: 18, color: '#4FD0E3', speed: 0.005, angle: Math.PI },
    { name: 'Neptune', orbitRadius: 290, size: 16, color: '#4B70DD', speed: 0.004, angle: Math.PI * 7/6 },
    { name: 'Pluto', orbitRadius: 320, size: 6, color: '#8C6239', speed: 0.003, angle: Math.PI * 4/3 },
    { name: 'Ceres', orbitRadius: 150, size: 4, color: '#C7C7C7', speed: 0.009, angle: Math.PI * 3/2 },
    { name: 'Eris', orbitRadius: 340, size: 7, color: '#A0A0A0', speed: 0.002, angle: Math.PI * 5/3 }
  ];

  // Create orbit circles and planet divs
  planets.forEach((planet, idx) => {
    // Create orbit path
    const orbit = document.createElement('div');
    orbit.classList.add('orbit-path');
    orbit.style.width = orbit.style.height = (planet.orbitRadius * 2) + 'px';
    orbitsContainer.appendChild(orbit);

    // Create planet
    const p = document.createElement('div');
    p.classList.add('planet');
    p.style.width = p.style.height = planet.size + 'px';
    p.style.backgroundColor = planet.color;
    p.dataset.idx = idx;
    p.dataset.planetName = planet.name;
    p.title = `Click to hear ${planet.name}`;
    
    // Add click event for planet sound
    p.addEventListener('click', () => {
      soundManager.playPlanetSound(planet.name);
      p.style.transform = 'scale(1.3)';
      setTimeout(() => {
        p.style.transform = 'scale(1)';
      }, 200);
    });
    
    // Add hover sound effect
    p.addEventListener('mouseenter', () => {
      soundManager.playUISound('hover');
    });
    
    orbitsContainer.appendChild(p);

    // Create planet label
    const label = document.createElement('div');
    label.classList.add('planet-label');
    label.textContent = planet.name;
    label.dataset.idx = idx;
    orbitsContainer.appendChild(label);
  });

  // Animate planets on their orbits
  function animatePlanets() {
    const centerX = solarWrapper.clientWidth / 2;
    const centerY = solarWrapper.clientHeight / 2;

    planets.forEach((planet, idx) => {
      planet.angle += planet.speed;
      if (planet.angle > 2 * Math.PI) planet.angle -= 2 * Math.PI;

      const planetDiv = orbitsContainer.querySelectorAll('.planet')[idx];
      const labelDiv = orbitsContainer.querySelectorAll('.planet-label')[idx];
      
      const x = centerX + planet.orbitRadius * Math.cos(planet.angle) - planet.size / 2;
      const y = centerY + planet.orbitRadius * Math.sin(planet.angle) - planet.size / 2;

      planetDiv.style.left = x + 'px';
      planetDiv.style.top = y + 'px';

      // Position label slightly offset from planet
      labelDiv.style.left = (x + planet.size + 5) + 'px';
      labelDiv.style.top = (y + planet.size / 2 - 5) + 'px';
    });

    requestAnimationFrame(animatePlanets);
  }

  animatePlanets();

  // Dance toggle on card with sound
  const card = document.getElementById("card");
  const trigger = document.getElementById("danceTrigger");
  let isDancing = false;
  trigger.addEventListener("click", () => {
    isDancing = !isDancing;
    card.classList.toggle("animate-bounce", isDancing);
    trigger.setAttribute("aria-pressed", isDancing);
    soundManager.playUISound('dance');
  });

  // Sound toggle button
  const soundToggle = document.getElementById('soundToggle');
  soundToggle.addEventListener('click', () => {
    const isEnabled = soundManager.toggle();
    soundToggle.textContent = isEnabled ? '🔊' : '🔇';
    soundToggle.classList.toggle('muted', !isEnabled);
    soundToggle.title = isEnabled ? 'Mute Sound' : 'Unmute Sound';
  });

  // Rain drops logic
  const rainContainer = document.getElementById("raindrop-container");
  function createRaindrop() {
    const drop = document.createElement("div");
    drop.classList.add("raindrop");
    drop.style.left = Math.random() * 100 + "%";
    drop.style.animationDuration = 0.8 + Math.random() * 0.5 + "s";
    rainContainer.appendChild(drop);
    setTimeout(() => rainContainer.removeChild(drop), 1500);
  }
  setInterval(createRaindrop, 100);

  // Leaves logic
  const leafContainer = document.getElementById("leaf-container");
  function createLeaf() {
    const leaf = document.createElement("div");
    leaf.classList.add("leaf");
    leaf.style.left = Math.random() * 100 + "%";
    leaf.style.animationDuration = 4 + Math.random() * 2 + "s";
    leafContainer.appendChild(leaf);
    setTimeout(() => leaf.remove(), 6000);
  }
  setInterval(createLeaf, 800);

  // Optional theme toggle button
  const themeToggleBtn = document.createElement('button');
  //themeToggleBtn.textContent = 'Toggle Theme';
  themeToggleBtn.className = "fixed top-4 right-4 z-50 bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-gray-200 px-4 py-2 rounded shadow";
  document.body.appendChild(themeToggleBtn);

  const htmlElement = document.documentElement;
  if (localStorage.getItem('theme') === 'dark' || (!('theme' in localStorage) && window.matchMedia('(prefers-color-scheme: dark)').matches)) {
    htmlElement.classList.add('dark');
  }
  themeToggleBtn.addEventListener('click', () => {
    htmlElement.classList.toggle('dark');
    soundManager.playUISound('click');
  });