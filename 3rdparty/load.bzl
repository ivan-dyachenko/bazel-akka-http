def declare_maven(hash):
    native.maven_jar(
        name = hash["name"],
        artifact = hash["artifact"],
        sha1 = hash["sha1"]
    )
    native.bind(name = hash["bind"], actual = hash["actual"])
