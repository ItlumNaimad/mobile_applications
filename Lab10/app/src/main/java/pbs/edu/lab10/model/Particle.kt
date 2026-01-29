package pbs.edu.lab10.model

data class Particle(
    val id: Int,
    val name: String,
    val symbol: String,
    val description: String,
    val imageUrls: List<String>
)

fun getParticles(): List<Particle> {
    return listOf(
        Particle(
            id = 1,
            name = "Electron",
            symbol = "e⁻",
            description = "A subatomic particle with a negative elementary electric charge. Electrons belong to the first generation of the lepton particle family.",
            imageUrls = listOf("https://assets.epuzzle.info/puzzle/117/698/original.webp")
        ),
        Particle(
            id = 2,
            name = "Proton",
            symbol = "p⁺",
            description = "A stable subatomic particle occurring in all atomic nuclei, with a positive electric charge equal in magnitude to that of an electron.",
            imageUrls = listOf("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimg.freepik.com%2Fpremium-vector%2Fillustration-proton-electron-neutron-atomic-structure-good-physics-science_786898-33.jpg%3Fw%3D900&f=1&nofb=1&ipt=52f4b357be1620afd250d1bdc1277dcf9b542cce670c8442c63bc11d27414bc9")
        ),
        Particle(
            id = 3,
            name = "Neutron",
            symbol = "n⁰",
            description = "A subatomic particle of about the same mass as a proton but without an electric charge.",
            imageUrls = listOf("https://www.infoescola.com/wp-content/uploads/2010/11/neutron-510x550.jpg" +
                    "")
        ),
        Particle(
            id = 4,
            name = "Photon",
            symbol = "γ",
            description = "A type of elementary particle that serves as the quantum of the electromagnetic field, including electromagnetic radiation such as light and radio waves.",
            imageUrls = listOf("https://cdn.zmescience.com/wp-content/uploads/2017/06/fbdabet.jpg")
        ),
        Particle(
            id = 5,
            name = "Higgs Boson",
            symbol = "H⁰",
            description = "An elementary particle in the Standard Model of particle physics produced by the quantum excitation of the Higgs field.",
            imageUrls = listOf("https://cdn.britannica.com/63/164163-050-6E18421E/event-proton-proton-collisions-CMS-detector-pair-Large-2012.jpg?w=300")
        ),
        Particle(
            id = 6,
            name = "Quark",
            symbol = "q",
            description = "A type of elementary particle and a fundamental constituent of matter. Quarks combine to form composite particles called hadrons, the most stable of which are protons and neutrons.",
            imageUrls = listOf("https://lovinthings.com/wp-content/uploads/2019/01/Quarks-Proton.jpg")
        ),
        Particle(
            id = 7,
            name = "Gluon",
            symbol = "g",
            description = "An elementary particle that acts as the exchange particle (or gauge boson) for the strong force between quarks. It is analogous to the exchange of photons in the electromagnetic force.",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/1/1f/Feynmann_Diagram_Gluon_Radiation.svg")
        ),
        Particle(
            id = 8,
            name = "Neutrino",
            symbol = "ν",
            description = "A lepton, an elementary particle with half-integer spin, that interacts only via the weak subatomic force and gravity.",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/5/57/FirstNeutrinoEventAnnotated.jpg")
        ),
        Particle(
            id = 9,
            name = "Muon",
            symbol = "μ⁻",
            description = "An elementary particle similar to the electron, with an electric charge of −1 e and a spin of 1/2, but with a much greater mass.",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/1/13/Muon_decay.svg/440px-Muon_decay.svg.png")
        ),
        Particle(
            id = 10,
            name = "Tau",
            symbol = "τ⁻",
            description = "A stable lepton with a negative electric charge and a spin of 1/2. It is the heaviest of the three charged leptons.",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Tau_lepton_decay.svg/440px-Tau_lepton_decay.svg.png")
        ),
        Particle(
            id = 11,
            name = "Z Boson",
            symbol = "Z⁰",
            description = "A gauge boson that mediates the weak interaction. It is electrically neutral and has a large mass.",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Standard_Model_of_Elementary_Particles.svg/440px-Standard_Model_of_Elementary_Particles.svg.png")
        ),
        Particle(
            id = 12,
            name = "W Boson",
            symbol = "W±",
            description = "A gauge boson that mediates the weak interaction. It exists in two charge states, W+ and W−.",
            imageUrls = listOf("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/W_boson_decay.svg/440px-W_boson_decay.svg.png")
        )
    )
}